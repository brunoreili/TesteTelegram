package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.cliente.ClienteRepository;
import app.bot.comanda.Comanda;
import app.bot.comanda.ComandaRepository;
import org.springframework.context.ApplicationContext;

public class EstadoApresentacao extends Estado{
    
    private final ClienteRepository clienteRepository;
    private final ComandaRepository comandaRepository;
    private Cliente cliente;
    private Comanda comanda;

    public EstadoApresentacao(ApplicationContext context, Cliente cliente, Comanda comanda) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
        this.clienteRepository = context.getBean(ClienteRepository.class);
        this.comandaRepository = context.getBean(ComandaRepository.class);
    }
    
    @Override
    public void processaMensagem(String mensagem) {
        
        try{
            
            mensagemResposta = "TESTEEEE " + comanda.isComandaAberta() + System.lineSeparator();
            
            if(comanda.isComandaAberta() == true){
            mensagemResposta = "Desculpe!\n" + 
                               "O fechamento da sua conta ainda está pendente!" +
                               "Tente de novo após confirmação de fechamanto da conta.";
            proximoEstado = this;
            }
             
            comanda.setComandaAberta(true);
            comanda = comandaRepository.save(comanda);
                                 
            if(cliente.getConsumoMedio() > 0.00 && cliente.getConsumoMedio() < 500.00){
            cliente.setCategoria("Prata");
            cliente = clienteRepository.save(cliente);
            }
            else if(cliente.getConsumoMedio() >= 500.00){
            cliente.setCategoria("Ouro");
            cliente = clienteRepository.save(cliente);
            }
                       
            if(cliente.getCategoria().equals("Bronze")){               
                mensagemResposta = "Olá, " + cliente.getFirst_name() + " " + cliente.getLast_name() + "!" + System.lineSeparator() +
                                   "Seja bem-vindo ao Laboratório do Chopp!" + System.lineSeparator() +
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
                                   "É muito bom te ter de volta!" + System.lineSeparator() +
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