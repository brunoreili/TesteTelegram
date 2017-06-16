package app.bot.estados;

import app.bot.dao.NaoAlcoolDAO;
import app.bot.dao.DrinkDAO;
import app.bot.cliente.Cliente;
import app.bot.comanda.Comanda;
import app.bot.dao.CervejaDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoBebidas extends Estado{

    private final CervejaDAO cervejaDAO = new CervejaDAO(context);
    private final DrinkDAO drinkDAO = new DrinkDAO(context);
    private final NaoAlcoolDAO naoAlcooDAO = new NaoAlcoolDAO(context);
    private final Cliente cliente;
    private final Comanda comanda;
    String escolha;
    List<String> opcoes;
    List<String> precos;
    
    public EstadoBebidas(ApplicationContext context, Cliente cliente, Comanda comanda) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
    }
    
    @Override
    public void processaMensagem(String mensagem) {
       
        switch (mensagem.trim()) {
            case "1":
                escolha = "Cerveja";
                
                opcoes = cervejaDAO.recuperaOpcoesCervejas();
                precos = cervejaDAO.recuperaValoresCervejas();                
                
                mensagemResposta = "Legal, temos:";                
                for(int i=0; i < opcoes.size() ; i++){
                    mensagemResposta += System.lineSeparator() + (i+1) + " - " + opcoes.get(i) + "..................R$ " + precos.get(i) + "0";
                }                
                proximoEstado = new EstadoCervejas(context, cliente, comanda, escolha); 
                
                break;
            case "2":
                escolha = "Drinks";
                
                opcoes = drinkDAO.recuperaOpcoesDrinks();
                precos = drinkDAO.recuperaValoresDrinks();
                        
                mensagemResposta = "Legal, temos:";                
                for(int i=0; i < opcoes.size() ; i++){
                    mensagemResposta += System.lineSeparator() + (i+1) + " - " + opcoes.get(i) + "..................R$ " + precos.get(i) + "0";
                }                
                proximoEstado = new EstadoDrinks(context, cliente, comanda, escolha); 
                
                break;
            case "3":
                escolha = "Não Alcoólicos";
                
                opcoes = naoAlcooDAO.recuperaOpcoesNaoAlcool();
                precos = naoAlcooDAO.recuperaValoresNaoAlcool();
                        
                mensagemResposta = "Legal, temos:";                
                for(int i=0; i < opcoes.size() ; i++){
                    mensagemResposta += System.lineSeparator() + (i+1) + " - " + opcoes.get(i) + "..................R$ " + precos.get(i) + "0";
                }                
                proximoEstado = new EstadoNaoAlcool(context, cliente, comanda, escolha); 
                
                break;
            default:
                mensagemResposta = "Por favor, escolha uma opção válida!";
                proximoEstado = this;
                break;    
        }
    }

}