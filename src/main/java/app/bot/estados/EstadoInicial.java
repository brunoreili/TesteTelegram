package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.comanda.Comanda;
import app.bot.dao.ComandaDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoInicial extends Estado{
    
    private final Cliente cliente;
    private final Comanda comanda;
    private final ComandaDAO comandaDAO = new ComandaDAO(context);

    public EstadoInicial(ApplicationContext context, Cliente cliente, Comanda comanda) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
    }

    @Override
    public void processaMensagem(String mensagem) {
        try{
            switch (mensagem.trim()) {
                case "1":
                    mensagemResposta = "Pois não, " + cliente.getFirst_name() + "." + System.lineSeparator() +
                                       "Escolha, entre as opções, o que deseja pedir?" + System.lineSeparator() +
                                       "Digite:" + System.lineSeparator() +
                                       "1 - Para BEBIDAS" + System.lineSeparator() +
                                       "2 - Para ESPETINHOS" + System.lineSeparator() +
                                       "3 - Para PORÇÕES";
                    proximoEstado = new EstadoEscolhendo(context, cliente, comanda);
                    break;
                case "2":                  
                    List<String> itens = comandaDAO.recuperaItensComanda();
                    List<Integer> quantidade = comandaDAO.recuperaQuantidadeItem();
                    List<Double> valor = comandaDAO.recuperaValorItem();
                    mensagemResposta =  cliente.getFirst_name() + ", sua comanda até o momento está assim:" + System.lineSeparator() + System.lineSeparator() +
                                        "QUANTIDADE - PRODUTO - VALOR" + System.lineSeparator();      
                    for(int i=0; i < itens.size() ; i++){
                    mensagemResposta += System.lineSeparator() + 
                                        quantidade.get(i) + " - " + 
                                        itens.get(i) + "........R$ " +
                                        valor.get(i) + "0";
                    }
                    mensagemResposta += System.lineSeparator() + 
                                        "TOTAL...................R$ " + comanda.getTotal() + "0" + System.lineSeparator() + System.lineSeparator() +
                                        "1 - Voltar" + System.lineSeparator() +
                                        "2 - Fechar comanda"
                                   
                                        + "\n\nTESTE!\n " + //APENAS PARA TESTAR
                                        "NOTA AVALIAÇÃO: " + cliente.getAvaliacao() + " \n" +
                                        "CONSUMO MÉDIO: " + cliente.getConsumoMedio() + " \n" +
                                        "CATEGORIA: " + cliente.getCategoria();  

                                        
                    proximoEstado = new EstadoDecidindo(context, cliente, comanda); //Colocar If para decidir se vai fechar a comanda ou voltar! //Analisar os parametros
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
    
}