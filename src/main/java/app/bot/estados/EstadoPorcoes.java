package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.comanda.Comanda;
import app.bot.dao.PorcaoDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoPorcoes extends Estado {
    
    private final PorcaoDAO porcoesDAO = new PorcaoDAO(context);
    private final Cliente cliente;
    private final Comanda comanda;
    private final String escolha;
    int item;

    public EstadoPorcoes(ApplicationContext context, Cliente cliente, Comanda comanda, String escolha) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
        this.escolha = escolha;
    }

    @Override
    public void processaMensagem(String mensagem) {
        
        List<String> porcao = porcoesDAO.recuperaOpcoesPorcoes();
        
        try{
            item = Integer.parseInt(mensagem) - 1;
            mensagemResposta = "Você escolheu " + porcao.get(item) + System.lineSeparator() +
                                "Quantas deseja?";
            proximoEstado = new EstadoQuantidade(context, cliente, comanda, escolha, item);
        }
        catch (Exception e){
            mensagemResposta = "Por favor, escolha uma opção válida!";
            proximoEstado = this;
        }
        
    }
    
}