package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.comanda.Comanda;
import org.springframework.context.ApplicationContext;

public class EstadoDecidindo extends Estado {
    
    private final Cliente cliente;
    private final Comanda comanda;
    private double desconto;
    private double totalDesconto;
    private double taxaServico;
    
    public EstadoDecidindo(ApplicationContext context, Cliente cliente, Comanda comanda) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
    }

    @Override
    public void processaMensagem(String mensagem) {
        
        try{
            switch (mensagem.trim()) {
                case "1":
                    mensagemResposta = "VOLTAR \n" +
                                       "1 - Voltar para o início. \n" +
                                       "2 - Olhar comanda.";
                    proximoEstado = new EstadoInicial(context, cliente, comanda);
                    break;
                case "2":
                    
                    if(cliente.getCategoria().equals("Bronze")){
                        desconto = 0;
                    }
                    if(cliente.getCategoria().equals("Prata")){
                        desconto = 0.1 * comanda.getTotal();
                    }
                    if(cliente.getCategoria().equals("Ouro")){
                        desconto = 0.25 * comanda.getTotal();
                    }
                    
                    totalDesconto = (comanda.getTotal() - desconto);
                    taxaServico = 0.1 * totalDesconto;
                    
                    mensagemResposta = "Certo, " + cliente.getFirst_name() + ".\n" +
                                       "O valor total da sua comanda é: R$"  + comanda.getTotal() + "0\n" +
                                       "Como você é um cliente " + cliente.getCategoria() + ", receberá um desconto de R$" + desconto + "0\n" +
                                       "Então, o valor final ficou: R$" + totalDesconto + "0\n" +
                                       "Caso queira pagar a taxa de serviço, o valor final da conta será de R$" + (totalDesconto + taxaServico) + "\n" +
                                       "1 - Pagar COM taxa de serviço.\n" +
                                       "2 - Pagar SEM taxa de serviço.";
                    proximoEstado = new EstadoFecharComanda(context, cliente, comanda, totalDesconto, taxaServico);
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