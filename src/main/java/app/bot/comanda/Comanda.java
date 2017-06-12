package app.bot.comanda;

import app.bot.cliente.Cliente;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Comanda {
    
    @Id
    private Integer id;
    @OneToOne
    private Cliente cliente;
    @OneToOne
    private ItemComanda item;
    private double total;
    private boolean pedidoAberto;
    private boolean comandaAberta;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ItemComanda getItem() {
        return item;
    }
    public void setItem(ItemComanda item) {
        this.item = item;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }   

    public boolean isPedidoAberto() {
        return pedidoAberto;
    }
    public void setPedidoAberto(boolean pedidoAberto) {
        this.pedidoAberto = pedidoAberto;
    }

    public boolean isComandaAberta() {
        return comandaAberta;
    }
    public void setComandaAberta(boolean comandaAberta) {
        this.comandaAberta = comandaAberta;
    }

}