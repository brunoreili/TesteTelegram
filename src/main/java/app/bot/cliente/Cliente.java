package app.bot.cliente;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente {
    
    @Id
    private Integer id;
    private String first_name;
    private String last_name;
    private String categoria;
    private Double consumo;
    private int avaliacao;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String status) {
        this.categoria = status;
    }
    
    public Double getConsumo() {
        return consumo;
    }
    public void setConsumo(Double consumo) {
        this.consumo = consumo;
    }

    public int getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    

}