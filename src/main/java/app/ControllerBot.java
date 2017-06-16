package app;

import app.bot.cliente.Cliente;
import app.bot.cliente.ClienteRepository;
import app.bot.comanda.Comanda;
import app.bot.comanda.ComandaRepository;
import app.bot.dao.CervejaDAO;
import app.bot.dao.DrinkDAO;
import app.bot.dao.EspetinhoDAO;
import app.bot.dao.NaoAlcoolDAO;
import app.bot.dao.PorcaoDAO;
import app.bot.estados.Estado;
import app.bot.estados.EstadoApresentacao;
import app.bot.model.Update;
import app.bot.sender.Sender;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerBot{
    
    @Autowired
    private ApplicationContext context;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ComandaRepository comandaRepository;
    
    public Map<Integer, Estado> estados = new HashMap<Integer, Estado>();
    private static final String BOT_ID = "374481790:AAHgscpBDG2zs4VsDbeg140VmSVZZeItPEw";
    
    @RequestMapping(method=RequestMethod.POST, value="/update")
    public Result ReceberUpdate(@RequestBody Update update){
        
        String mensagem = update.getMessage().getText();
        int user_id = update.getMessage().getFrom().getId();
        
        Cliente cliente = buscarCliente(update);
        Comanda comanda = buscarComanda(cliente);
        
        Estado estado = estados.get(user_id);
        if (estado == null){
            estado = new EstadoApresentacao(context, cliente, comanda);
        }
       
        estado.processaMensagem(mensagem);        
        estados.put(user_id, estado.getProximoEstado());

        if(user_id > 100) // significa que estamos fazendo testes. O userID do telegram tem varios digitos. Nesse caso nao enviaremos a resposta via telegram
        {
            try {
                new Sender(BOT_ID).sendResponse(user_id, estado.getMensagemResposta());
            } catch (IOException ex) {
                Logger.getLogger(ControllerBot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Cadastro no banco de itens iniciais!
        EspetinhoDAO bancoEspetinho = new EspetinhoDAO(context);
        bancoEspetinho.cadastraEspetinhos();
        
        PorcaoDAO bancoPorcao = new PorcaoDAO(context);
        bancoPorcao.cadastraPorcoes();
        
        CervejaDAO bancoCerveja = new CervejaDAO(context);
        bancoCerveja.cadastraCervejas();
        
        DrinkDAO bancoDrink = new DrinkDAO(context);
        bancoDrink.cadastraDrink();
        
        NaoAlcoolDAO bancoNaoAlcool = new NaoAlcoolDAO(context);
        bancoNaoAlcool.cadastraNaoAlcool();
        
        return new Result(estado.getMensagemResposta());
    }  

    private Cliente buscarCliente(Update update) {
        
        Integer idCliente = update.getMessage().getFrom().getId();
        Cliente cliente = clienteRepository.findOne(idCliente);
        
        if(cliente == null || cliente.getConsumoMedio() == 0.00){
            Cliente novo = new Cliente();
            novo.setFirst_name(update.getMessage().getFrom().getFirst_name());
            novo.setLast_name(update.getMessage().getFrom().getLast_name());
            novo.setId(idCliente);
            novo.setCategoria("Bronze");
            novo.setConsumoMedio(0.00);
            cliente = clienteRepository.save(novo);
        }
                        
        return cliente;
        
    }
    private Comanda buscarComanda(Cliente cliente){
        
        Comanda comanda = comandaRepository.findOne(cliente.getId());
        
        if(comanda == null){
            Comanda nova = new Comanda();
            nova.setId(cliente.getId());
            nova.setCliente(cliente);
            nova.setItem(null);
            nova.setTotal(0);
            nova.setPedidoAberto(false);
            nova.setComandaAberta(false);
            comanda = comandaRepository.save(nova);
        }
        
        return comanda;
    }
}