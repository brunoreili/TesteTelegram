package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.comanda.Comanda;
import app.bot.dao.EspetinhoDAO;
import app.bot.dao.PorcaoDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoEscolhendo extends Estado {

    private final EspetinhoDAO espetinhoDAO = new EspetinhoDAO(context);
    private final PorcaoDAO porcoesDAO = new PorcaoDAO(context);
    private final Cliente cliente;
    private final Comanda comanda;
    String escolha;
    List<String> opcoes;
    List<String> precos;

    public EstadoEscolhendo(ApplicationContext context, Cliente cliente, Comanda comanda) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
    }    
    
    @Override
    public void processaMensagem(String mensagem) {
        
        switch (mensagem.trim()) {
            case "1":
                mensagemResposta = "BEBIDAS" + System.lineSeparator() +
                                   "Certo, " + cliente.getFirst_name() + ", temos as bebidas:" + System.lineSeparator() +
                                   "1 - CERVEJAS" + System.lineSeparator() +
                                   "2 - DRINKS" + System.lineSeparator() +
                                   "3 - NÃO ALCOÓLICOS" + System.lineSeparator() +
                                   "O que deseja?";
                proximoEstado = new EstadoBebidas(context, cliente, comanda);
                break;
            case "2":
                escolha = "Espetinho";
                
                opcoes = espetinhoDAO.recuperaOpcoesEspetinhos();
                precos = espetinhoDAO.recuperaValoresEspetinhos();
                
                    mensagemResposta = "Legal, temos:";
                    for(int i=0; i < opcoes.size() ; i++){
                        mensagemResposta += System.lineSeparator() + (i+1) + " - " + opcoes.get(i) + "..................R$ " + precos.get(i) + "0";
                }
                proximoEstado = new EstadoEspetinho(context, cliente, comanda, escolha);
                break;
            case "3":
                escolha = "Porções";
                
                opcoes = porcoesDAO.recuperaOpcoesPorcoes();
                precos = porcoesDAO.recuperaValoresPorcoes();

                    mensagemResposta = "Legal, temos:";
                    for(int i=0; i < opcoes.size() ; i++){
                        mensagemResposta += System.lineSeparator() + (i+1) + " - " + opcoes.get(i) + "..................R$ " + precos.get(i) + "0";
                    }
                    proximoEstado = new EstadoPorcoes(context, cliente, comanda, escolha);
                    break;
            default:
                mensagemResposta = "Por favor, escolha uma opção válida!";
                proximoEstado = this;
                break;
        }
        
    }
    
}