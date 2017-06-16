package app.bot.dao;

import app.bot.cardapio.ItemEspetinho;
import app.bot.cardapio.ItemEspetinhoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EspetinhoDAO {
    
    private final ApplicationContext context;
    private ItemEspetinhoRepository itemEspetinhoRepository;

    public EspetinhoDAO(ApplicationContext context) {
        this.context = context;
    }
    
        public void cadastraEspetinhos(){
        
        itemEspetinhoRepository = context.getBean(ItemEspetinhoRepository.class);
        
        if("[]".equals(itemEspetinhoRepository.findAll().toString())){
            
            ItemEspetinho e1 = new ItemEspetinho();
            e1.setNome("Espetinho de Picanha");
            e1.setValor("10.0");        
            itemEspetinhoRepository.save(e1);
                
            ItemEspetinho e2 = new ItemEspetinho();
            e2.setNome("Espetinho de Carne de Sol");
            e2.setValor("7.0");        
            itemEspetinhoRepository.save(e2);
        
            ItemEspetinho e3 = new ItemEspetinho();
            e3.setNome("Espetinho de Frango c/ Bacon");
            e3.setValor("7.0");        
            itemEspetinhoRepository.save(e3);   
            
            ItemEspetinho e4 = new ItemEspetinho();
            e4.setNome("Espetinho de Linguiça");
            e4.setValor("5.0");        
            itemEspetinhoRepository.save(e4);
        }
    }
    
    public List<String> recuperaOpcoesEspetinhos() {
        
        itemEspetinhoRepository = context.getBean(ItemEspetinhoRepository.class);        
        Iterable<ItemEspetinho> espetinhos = itemEspetinhoRepository.findAll();    
        List<String> result = new ArrayList<>();
        
        for(ItemEspetinho espetinho : espetinhos){
            result.add(espetinho.getNome());
        }
    
        return result;
    }    

    public List<String> recuperaValoresEspetinhos() {
        
        itemEspetinhoRepository = context.getBean(ItemEspetinhoRepository.class);        
        Iterable<ItemEspetinho> espetinhos = itemEspetinhoRepository.findAll();    
        List<String> result = new ArrayList<>();
        
        for(ItemEspetinho espetinho : espetinhos){
            result.add(espetinho.getValor());
        }
    
        return result;
    }
    
}