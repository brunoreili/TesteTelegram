package app.bot.dao;

import app.bot.cardapio.ItemNaoAlcool;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import app.bot.cardapio.ItemNaoAlcoolRepository;

public class NaoAlcoolDAO {
    
    private final ApplicationContext context;
    private ItemNaoAlcoolRepository itemNaoAlcoolRepository;

    public NaoAlcoolDAO(ApplicationContext context) {
        this.context = context;
    }

    public void cadastraNaoAlcool(){
        
        itemNaoAlcoolRepository = context.getBean(ItemNaoAlcoolRepository.class);
        
        if("[]".equals(itemNaoAlcoolRepository.findAll().toString())){
            
            ItemNaoAlcool na1 = new ItemNaoAlcool();
            na1.setNome("Coca-cola");
            na1.setValor("3.0");        
            itemNaoAlcoolRepository.save(na1);
                
            ItemNaoAlcool na2 = new ItemNaoAlcool();
            na2.setNome("Guaraná Antártica");
            na2.setValor("3.0");        
            itemNaoAlcoolRepository.save(na2);
        
            ItemNaoAlcool na3 = new ItemNaoAlcool();
            na3.setNome("Suco de Laranja");
            na3.setValor("4.0");        
            itemNaoAlcoolRepository.save(na3);
            
            ItemNaoAlcool na4 = new ItemNaoAlcool();
            na4.setNome("Limonada Suiça");
            na4.setValor("4.0");        
            itemNaoAlcoolRepository.save(na4);
            
            ItemNaoAlcool na5 = new ItemNaoAlcool();
            na5.setNome("Água");
            na5.setValor("2.0");        
            itemNaoAlcoolRepository.save(na5);
        }
        
    }
    
    public List<String> recuperaOpcoesNaoAlcool() {
        
        itemNaoAlcoolRepository = context.getBean(ItemNaoAlcoolRepository.class);        
        Iterable<ItemNaoAlcool> naoAlcools = itemNaoAlcoolRepository.findAll();    
        List<String> result = new ArrayList<>();
        
        for(ItemNaoAlcool naoAlcool : naoAlcools){
            result.add(naoAlcool.getNome());
        }
    
        return result;         
    }

    public List<String> recuperaValoresNaoAlcool() {
        
        itemNaoAlcoolRepository = context.getBean(ItemNaoAlcoolRepository.class);        
        Iterable<ItemNaoAlcool> naoAlcools = itemNaoAlcoolRepository.findAll();    
        List<String> result = new ArrayList<>();
        
        for(ItemNaoAlcool naoAlcool : naoAlcools){
            result.add(naoAlcool.getValor());
        }
        
        return result;
    }
    
}
