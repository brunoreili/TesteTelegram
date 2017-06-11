package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.comanda.Comanda;
import app.bot.dao.EspetinhoDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoEspetinho extends Estado {
    
    private final EspetinhoDAO espetinhoDAO = new EspetinhoDAO(context);
    private final Cliente cliente;
    private final Comanda comanda;
    private final String escolha;
    int item;

    public EstadoEspetinho(ApplicationContext context, Cliente cliente, Comanda comanda, String escolha) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
        this.escolha = escolha;
    }
    
    @Override
    public void processaMensagem(String mensagem) {
        
        List<String> espetinho = espetinhoDAO.recuperaOpcoesEspetinhos();
        
        try{
            item = Integer.parseInt(mensagem) - 1;
            mensagemResposta = "legal, você escolheu " + espetinho.get(item) + System.lineSeparator() +
                                "Quantos deseja?";
            proximoEstado = new EstadoQuantidade(context, cliente, comanda, escolha, item);
        }
        catch (Exception e){
            mensagemResposta = "Por favor, escolha uma opção válida!";
            proximoEstado = this;
        }
        
    }
            
}