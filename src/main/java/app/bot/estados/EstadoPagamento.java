package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.cliente.ClienteRepository;
import app.bot.comanda.Comanda;
import app.bot.comanda.ComandaRepository;
import app.bot.comanda.ItemComandaRepository;
import app.bot.dao.ComandaDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoPagamento extends Estado {
    
    private final ComandaDAO comandaDAO = new ComandaDAO(context);
    private final ClienteRepository clienteRepository;
    private final ComandaRepository comandaRepository;
    private final ItemComandaRepository itemComandaRepository;
    final private Cliente cliente;
    final private Comanda comanda;    
    
    EstadoPagamento(ApplicationContext context, Cliente cliente, Comanda comanda) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
        this.clienteRepository = context.getBean(ClienteRepository.class);
        this.comandaRepository = context.getBean(ComandaRepository.class);
        this.itemComandaRepository = context.getBean(ItemComandaRepository.class);
    }

    @Override
    public void processaMensagem(String mensagem) {
        
        try{
            switch (mensagem.trim()) {
                case "1":
                    deletaComanda();
                    salvaAvaliacao(1);
                    mensagemResposta = "Muito obrigado, " + cliente.getFirst_name() + ".\n" +
                                       "O Laboratório do Chopp agradece a sua vinda!\n" +
                                       "Esperamos que volte mais vezes!";                                   
                    proximoEstado = new EstadoApresentacao(context, cliente, comanda);
                    break;
                case "2":
                    deletaComanda();
                    
                    List<String> itens = comandaDAO.recuperaItensComanda();
                    mensagemResposta = "ITENS " + itens.size();
                    
                    salvaAvaliacao(2);
                    mensagemResposta += "Muito obrigado, " + cliente.getFirst_name() + ".\n" +
                                       "O Laboratório do Chopp agradece a sua vinda!\n" +
                                       "Esperamos que volte mais vezes!";                                   
                    proximoEstado = new EstadoApresentacao(context, cliente, comanda);      
                    break;
                case "3":
                    deletaComanda();
                    salvaAvaliacao(3);
                    mensagemResposta = "Muito obrigado, " + cliente.getFirst_name() + ".\n" +
                                       "O Laboratório do Chopp agradece a sua vinda!\n" +
                                       "Esperamos que volte mais vezes!";                                   
                    proximoEstado = new EstadoApresentacao(context, cliente, comanda);
                    break;
                case "4":
                    deletaComanda();
                    salvaAvaliacao(4);
                    mensagemResposta = "Muito obrigado, " + cliente.getFirst_name() + ".\n" +
                                       "O Laboratório do Chopp agradece a sua vinda!\n" +
                                       "Esperamos que volte mais vezes!";                                   
                    proximoEstado = new EstadoApresentacao(context, cliente, comanda);
                    break;
                case "5":
                    deletaComanda();
                    salvaAvaliacao(5);
                    mensagemResposta = "Muito obrigado," + cliente.getFirst_name() + ".\n" +
                                       "O Laboratório do Chopp agradece a sua vinda!\n" +
                                       "Esperamos que volte mais vezes!";                                   
                    proximoEstado = new EstadoApresentacao(context, cliente, comanda);
                    break;
            default:
                    mensagemResposta = "Por favor, escolha uma opção válida!";
                    proximoEstado = this;
                    break;
            }
        }catch(Exception e){
                    mensagemResposta = "Por favor, escolha uma opção válida!";
                    proximoEstado = this;
        }
        
    }

    private void salvaAvaliacao(int i) {
        
        cliente.setAvaliacao(i);
        clienteRepository.save(cliente);
        
    }

    private void deletaComanda() {
        
        comanda.setTotal(0);
        comandaRepository.save(comanda);
        
        comandaDAO.deletaItem();
          
    }
    
}