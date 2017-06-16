package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.cliente.ClienteRepository;
import app.bot.comanda.Comanda;
import app.bot.comanda.ComandaRepository;
import org.springframework.context.ApplicationContext;

public class EstadoFecharComanda extends Estado {
    
    private final ClienteRepository clienteRepository; 
    private final ComandaRepository comandaRepository;
    private final Cliente cliente;
    private final Comanda comanda;
    private final double totalDesconto;
    private final double taxaServico;
    private double valorPagamento;

    public EstadoFecharComanda(ApplicationContext context, Cliente cliente, Comanda comanda, double totalDesconto, double taxaServico) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
        this.totalDesconto = totalDesconto;
        this.taxaServico = taxaServico;
        this.clienteRepository = context.getBean(ClienteRepository.class);
        this.comandaRepository = context.getBean(ComandaRepository.class);
    }

    @Override
    public void processaMensagem(String mensagem) {
        
        try{
            switch (mensagem.trim()) {
                case "1":
                    if(cliente.getConsumoMedio() == 0.00){
                        cliente.setConsumoMedio(cliente.getConsumoMedio() + comanda.getTotal());
                    }
                    else{
                        cliente.setConsumoMedio((cliente.getConsumoMedio() + comanda.getTotal()) / 2);
                    }
                    salvaConsumoMedio();
                    
                    valorPagamento = totalDesconto + taxaServico;
                    salvaTotal();
                    mensagemResposta = "Tudo bem então" + System.lineSeparator() +
                                       "Estamos indo à sua mesa para receber o pagamento!"  + System.lineSeparator() +
                                       "Enquanto isso, avalie nosso atendimento."  + System.lineSeparator() +
                                       "1 - PÉSSIMO" + System.lineSeparator() +
                                       "2 - RUIM" + System.lineSeparator() + 
                                       "3 - REGULAR" + System.lineSeparator() +
                                       "4 - BOM" + System.lineSeparator() +
                                       "5 - ÓTIMO";                                   
                    proximoEstado = new EstadoPagamento(context, cliente, comanda);
                    break;
                case "2":
                    if(cliente.getConsumoMedio() == 0.00){
                        cliente.setConsumoMedio(cliente.getConsumoMedio() + comanda.getTotal());
                    }
                    else{
                        cliente.setConsumoMedio((cliente.getConsumoMedio() + comanda.getTotal()) / 2);
                    }
                    salvaConsumoMedio();
                    
                    valorPagamento = totalDesconto;
                    salvaTotal();
                    mensagemResposta = "Tudo bem então" + System.lineSeparator() +
                                       "Estamos indo à sua mesa para receber o pagamento!"  + System.lineSeparator() +
                                       "Enquanto isso, avalie nosso atendimento."  + System.lineSeparator() +
                                       "1 - PÉSSIMO" + System.lineSeparator() +
                                       "2 - RUIM" + System.lineSeparator() + 
                                       "3 - REGULAR" + System.lineSeparator() +
                                       "4 - BOM" + System.lineSeparator() +
                                       "5 - ÓTIMO";
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

    private void salvaTotal() {
        
        comanda.setTotal(valorPagamento);
        comandaRepository.save(comanda);
        
    }

}