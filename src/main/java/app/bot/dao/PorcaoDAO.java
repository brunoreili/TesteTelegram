package app.bot.dao;

import app.bot.cardapio.ItemPorcao;
import app.bot.cardapio.ItemPorcaoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class PorcaoDAO {
    
    private final ApplicationContext context;
    private ItemPorcaoRepository itemPorcaoRepository;
    
    public PorcaoDAO(ApplicationContext context) {
        this.context = context;
    }
    
        public void cadastraPorcoes(){
        
        itemPorcaoRepository = context.getBean(ItemPorcaoRepository.class);
        
        if("[]".equals(itemPorcaoRepository.findAll().toString())){
            
            ItemPorcao i1 = new ItemPorcao();
            i1.setNome("Batata Frita");
            i1.setValor("7.0");
            itemPorcaoRepository.save(i1);
                
            ItemPorcao i2 = new ItemPorcao();
            i2.setNome("Carne de sol c/ Mandioca");
            i2.setValor("10.0");        
            itemPorcaoRepository.save(i2);
        
            ItemPorcao i3 = new ItemPorcao();
            i3.setNome("Camarão internacional");
            i3.setValor("13.0");        
            itemPorcaoRepository.save(i3);   
        }
    }

    public List<String> recuperaOpcoesPorcoes() {
        
        itemPorcaoRepository = context.getBean(ItemPorcaoRepository.class);        
        Iterable<ItemPorcao> porcoes = itemPorcaoRepository.findAll();    
        List<String> result = new ArrayList<>();
        
        for(ItemPorcao porcao : porcoes){
            result.add(porcao.getNome());
        }
    
        return result;
    }
    
    public List<String> recuperaValoresPorcoes() {
        
        itemPorcaoRepository = context.getBean(ItemPorcaoRepository.class);        
        Iterable<ItemPorcao> porcoes = itemPorcaoRepository.findAll();    
        List<String> result = new ArrayList<>();
        
        for(ItemPorcao porcao : porcoes){
            result.add(porcao.getValor());
        }
    
        return result;
    }
    
}