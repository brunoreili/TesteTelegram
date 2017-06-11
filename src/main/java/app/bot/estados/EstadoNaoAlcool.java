package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.comanda.Comanda;
import app.bot.dao.NaoAlcoolDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoNaoAlcool extends Estado {
    
    private final NaoAlcoolDAO naoAlcoolDAO = new NaoAlcoolDAO(context);
    private final Cliente cliente;
    private final Comanda comanda;
    private final String escolha;
    int item;

    public EstadoNaoAlcool(ApplicationContext context, Cliente cliente, Comanda comanda, String escolha) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
        this.escolha = escolha;
    }

    @Override
    public void processaMensagem(String mensagem) {
        
        List<String> cerveja = naoAlcoolDAO.recuperaOpcoesNaoAlcool();
        
        try{
            item = Integer.parseInt(mensagem) - 1;
            mensagemResposta = "Você escolheu " + cerveja.get(item) + System.lineSeparator() +
                                "Quantas deseja?";
            proximoEstado = new EstadoQuantidade(context, cliente, comanda, escolha, item);
        }
        catch (Exception e){
            mensagemResposta = "Por favor, escolha uma opção válida!";
            proximoEstado = this;
        }
        
    }

}