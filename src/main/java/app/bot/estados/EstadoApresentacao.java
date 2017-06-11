package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.comanda.Comanda;
import org.springframework.context.ApplicationContext;

public class EstadoApresentacao extends Estado{
    
    private final Cliente cliente;
    private final Comanda comanda;

    public EstadoApresentacao(ApplicationContext context, Cliente cliente, Comanda comanda) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
        
    }
    
    @Override
    public void processaMensagem(String mensagem) {
        
        try{
            if(cliente.getCategoria().equals("Bronze")){
            
                mensagemResposta = "Olá, " + cliente.getFirst_name() + " " + cliente.getLast_name() + "!" + System.lineSeparator() +
                                   "Seja bem-vindo  ao Laboratório do Chopp!" + System.lineSeparator() +
                                   "É muito bom ter você como novo cliente do nosso Bar!" + System.lineSeparator() +
                                   "Sou o seu garçom virtual e estou aqui para ajudá-lo a escolher seu pedido." + System.lineSeparator() +
                                   "Você é um cliente \"" + cliente.getCategoria() + "\"" + System.lineSeparator() +
                                   "Compareça mais vezes para se tornar um cliente \"Prata\" ou \"Ouro\" e terá descontos especiais!" + System.lineSeparator() + 
                                   "Vamos lá, o que deseja fazer?" + System.lineSeparator() +
                                   "Digite: " + System.lineSeparator() +
                                   "1 - Olhar cardápio" + System.lineSeparator() +
                                   "2 - Olhar/Fechar comanda";
            }else{
                mensagemResposta = "Olá, " + cliente.getFirst_name() + " " + cliente.getLast_name() + "!" + System.lineSeparator() +
                                   "Seja bem-vindo ao Laboratório do Chopp!" + System.lineSeparator() +
                                   "É muito bom tê-lo de volta!" + System.lineSeparator() +
                                   "Você é um cliente \"" + cliente.getCategoria() + "\" e terá descontos especiais ao fechar a comanda!" + System.lineSeparator() + 
                                   "Vamos lá, o que deseja fazer?" + System.lineSeparator() +
                                   "Digite: " + System.lineSeparator() +
                                   "1 - Olhar cardápio" + System.lineSeparator() +
                                   "2 - Olhar/Fechar comanda";
            }
            proximoEstado = new EstadoInicial(context, cliente, comanda);
        }catch(Exception e){
            mensagemResposta = "Não entendi, diga alguma coisa!";
            proximoEstado = this;
        }       
     
    }
    
}