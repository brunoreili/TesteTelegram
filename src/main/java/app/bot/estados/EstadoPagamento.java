package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.cliente.ClienteRepository;
import app.bot.comanda.Comanda;
import org.springframework.context.ApplicationContext;

public class EstadoPagamento extends Estado {
    
    private final ClienteRepository clienteRepository;
    final private Cliente cliente;
    final private Comanda comanda;
    
    EstadoPagamento(ApplicationContext context, Cliente cliente, Comanda comanda) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
        this.clienteRepository = context.getBean(ClienteRepository.class);
    }

    @Override
    public void processaMensagem(String mensagem) {
        
        try{
            switch (mensagem.trim()) {
                case "1":
                    salvaAvaliacao(1);
                    mensagemResposta = "Muito obrigado," + cliente.getFirst_name() + ".\n" +
                                       "O Laboratório do Chopp agradece a sua vinda!\n" +
                                       "Esperamos que volte mais vezes!";                                   
                    proximoEstado = new EstadoApresentacao(context, cliente, comanda);
                    break;
                case "2":
                    salvaAvaliacao(2);
                    mensagemResposta = "Muito obrigado," + cliente.getFirst_name() + ".\n" +
                                       "O Laboratório do Chopp agradece a sua vinda!\n" +
                                       "Esperamos que volte mais vezes!";                                   
                    proximoEstado = new EstadoApresentacao(context, cliente, comanda);      
                    break;
                case "3":
                    salvaAvaliacao(3);
                    mensagemResposta = "Muito obrigado," + cliente.getFirst_name() + ".\n" +
                                       "O Laboratório do Chopp agradece a sua vinda!\n" +
                                       "Esperamos que volte mais vezes!";                                   
                    proximoEstado = null;
                    break;
                case "4":
                    salvaAvaliacao(4);
                    mensagemResposta = "Muito obrigado," + cliente.getFirst_name() + ".\n" +
                                       "O Laboratório do Chopp agradece a sua vinda!\n" +
                                       "Esperamos que volte mais vezes!";                                   
                    proximoEstado = null;
                    break;
                case "5":
                    salvaAvaliacao(5);
                    mensagemResposta = "Muito obrigado," + cliente.getFirst_name() + ".\n" +
                                       "O Laboratório do Chopp agradece a sua vinda!\n" +
                                       "Esperamos que volte mais vezes!";                                   
                    proximoEstado = null;
                    break;
            default:
                    mensagemResposta = "Por favor, escolha uma opção válida!";
                    proximoEstado = null;
                    break;
            }
        }catch(Exception e){
                    mensagemResposta = "Por favor, escolha uma opção válida!";
                    proximoEstado = this;
        }
        
    }

    private void salvaAvaliacao(int i) {
        
        cliente.setAvaliacao(i);
        clienteRepository.save(cliente);
        
    }
    
}