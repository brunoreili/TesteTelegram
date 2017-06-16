package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.comanda.Comanda;
import app.bot.dao.CervejaDAO;
import app.bot.dao.DrinkDAO;
import app.bot.dao.EspetinhoDAO;
import app.bot.dao.NaoAlcoolDAO;
import app.bot.dao.PorcaoDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoQuantidade extends Estado{
    
    private final EspetinhoDAO espetinhoDAO = new EspetinhoDAO(context);
    private final PorcaoDAO porcaoDAO = new PorcaoDAO(context);
    private final CervejaDAO cervejaDAO = new CervejaDAO(context);
    private final DrinkDAO drinkDAO = new DrinkDAO(context);
    private final NaoAlcoolDAO naoAlcoolDAO = new NaoAlcoolDAO(context);
    private final Cliente cliente;
    private final Comanda comanda;
    private final String escolha;
    private final int item;    
    String produto;
    int quantidade;
    double valor;
    
    public EstadoQuantidade(ApplicationContext context, Cliente cliente, Comanda comanda, String escolha, int item){
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
        this.escolha = escolha;
        this.item = item;
    }   
    
    @Override
    public void processaMensagem(String mensagem) {
                       
        try{
            //Seleções
            if(escolha.equals("Espetinho")){
                List<String> espetinho = espetinhoDAO.recuperaOpcoesEspetinhos();
                List<String> preco = espetinhoDAO.recuperaValoresEspetinhos();
                
                produto = espetinho.get(item);
                quantidade = Integer.parseInt(mensagem.trim());
                valor = Double.parseDouble(preco.get(item)) * quantidade;
            }
            if(escolha.equals("Porções")){
                List<String> porcao = porcaoDAO.recuperaOpcoesPorcoes();
                List<String> preco = porcaoDAO.recuperaValoresPorcoes();
                
                produto = porcao.get(item);
                quantidade = Integer.parseInt(mensagem.trim());
                valor = Double.parseDouble(preco.get(item)) * quantidade;
            }
            if (escolha.equals("Cerveja")){                
                List<String> cerveja = cervejaDAO.recuperaOpcoesCervejas();
                List<String> preco = cervejaDAO.recuperaValoresCervejas();
                
                produto = cerveja.get(item);
                quantidade = Integer.parseInt(mensagem.trim());
                valor = Double.parseDouble(preco.get(item)) * quantidade;
            }
            if (escolha.equals("Drinks")){                
                List<String> cerveja = drinkDAO.recuperaOpcoesDrinks();
                List<String> preco = drinkDAO.recuperaValoresDrinks();
                
                produto = cerveja.get(item);
                quantidade = Integer.parseInt(mensagem.trim());
                valor = Double.parseDouble(preco.get(item)) * quantidade;
            }
            if (escolha.equals("Não Alcoólicos")){                
                List<String> cerveja = naoAlcoolDAO.recuperaOpcoesNaoAlcool();
                List<String> preco = naoAlcoolDAO.recuperaValoresNaoAlcool();
                
                produto = cerveja.get(item);
                quantidade = Integer.parseInt(mensagem.trim());
                valor = Double.parseDouble(preco.get(item)) * quantidade;
            } 
            
            //Quantidade
            if(quantidade == 1){                
                mensagemResposta = "Beleza, anotei! " + quantidade + " unidade de " + produto + System.lineSeparator() +
                                   "O valor total ficou em: R$ " + valor + "0" + System.lineSeparator() +
                                   "Posso confirmar ou deseja pedir algo a mais?" + System.lineSeparator() +
                                   "1 - Pode confirmar o pedido!" + System.lineSeparator() +
                                   "2 - Quero pedir algo a mais!";
                proximoEstado = new EstadoConfirma(context, cliente, comanda, produto, quantidade, valor);    
            }
            else if(quantidade > 1 && quantidade <= 20){                
                mensagemResposta = "Beleza, Anotei! São " + quantidade + " unidades de " + produto + System.lineSeparator() +
                                   "O valor total ficou em: R$ " + valor + "0" + System.lineSeparator()  +
                                   "Posso confirmar ou deseja pedir algo a mais?" + System.lineSeparator() +
                                   "1 - Pode confirmar o pedido!" + System.lineSeparator() +
                                   "2 - Quero pedir algo a mais!";
                proximoEstado = new EstadoConfirma(context, cliente, comanda, produto, quantidade, valor);                
            }
            else if(quantidade > 20){
                mensagemResposta = "Desculpe, só posso trazer no máximo 20 unidades deste pedido!" + System.lineSeparator() +
                                   "Por favor, tente uma quantidade menor.";
                proximoEstado = this;
            }
            else{
                mensagemResposta = "Vamos lá, preciso de um valor positivo!" + System.lineSeparator() + 
                                   "Por favor, tente de novo.";
                proximoEstado = this;
            }
            
        }
        catch (Exception e){
            mensagemResposta = "Vamos lá, preciso de um valor positivo!" + System.lineSeparator() + 
                               "Por favor, tente de novo.";
            proximoEstado = this;
        }
     
    }
    
}