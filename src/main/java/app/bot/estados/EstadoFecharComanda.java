package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.cliente.ClienteRepository;
import app.bot.comanda.Comanda;
import org.springframework.context.ApplicationContext;

public class EstadoFecharComanda extends Estado {
    
    private final ClienteRepository clienteRepository; 
    private final Cliente cliente;
    private final Comanda comanda;
    private double taxaServico; //receber da classe anterior

    public EstadoFecharComanda(ApplicationContext context, Cliente cliente, Comanda comanda, double taxaServico) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
        this.taxaServico = taxaServico;
        this.clienteRepository = context.getBean(ClienteRepository.class);
    }

    @Override
    public void processaMensagem(String mensagem) {
        
        try{
            switch (mensagem.trim()) {
                case "1":
                    taxaServico = 5;
                    cliente.setConsumoMedio(comanda.getTotal() + taxaServico);
                    salvaConsumoMedio();
                    mensagemResposta = "Tudo bem então" + System.lineSeparator() +
                                       "Estamos indo à sua mesa para receber o pagamento!"  + System.lineSeparator() +
                                       "Enquanto isso, avalie nosso atendimento."  + System.lineSeparator() +
                                       "1 - PÉSSIMO" + System.lineSeparator() +
                                       "2 - RUIM" + System.lineSeparator() + 
                                       "3 - REGULAR" + System.lineSeparator() +
                                       "4 - BOM" + System.lineSeparator() +
                                       "5 – ÓTIMO";                                   
                    proximoEstado = new EstadoPagamento(context, cliente, comanda);
                    break;
                case "2":
                    cliente.setConsumoMedio(comanda.getTotal());
                    salvaConsumoMedio();
                    mensagemResposta = "Tudo bem então" + System.lineSeparator() +
                                       "Estamos indo à sua mesa para receber o pagamento!"  + System.lineSeparator() +
                                       "Enquanto isso, avalie nosso atendimento."  + System.lineSeparator() +
                                       "1 - PÉSSIMO" + System.lineSeparator() +
                                       "2 - RUIM" + System.lineSeparator() + 
                                       "3 - REGULAR" + System.lineSeparator() +
                                       "4 - BOM" + System.lineSeparator() +
                                       "5 – ÓTIMO";
                    proximoEstado = new EstadoPagamento(context, cliente, comanda);
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

    private void salvaConsumoMedio() {

        clienteRepository.save(cliente);
        
    }

}