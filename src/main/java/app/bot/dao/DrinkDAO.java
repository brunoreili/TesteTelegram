package app.bot.dao;

import app.bot.cardapio.ItemDrink;
import app.bot.cardapio.ItemDrinkRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class DrinkDAO {
    
    private final ApplicationContext context;
    private ItemDrinkRepository itemDrinkRepository;

    public DrinkDAO(ApplicationContext context) {
        this.context = context;
    }

    public void cadastraDrink(){
        
        itemDrinkRepository = context.getBean(ItemDrinkRepository.class);
        
        if("[]".equals(itemDrinkRepository.findAll().toString())){
            
            ItemDrink d1 = new ItemDrink();
            d1.setNome("Whisky");
            d1.setValor("15.0");        
            itemDrinkRepository.save(d1);
                
            ItemDrink d2 = new ItemDrink();
            d2.setNome("Caipirinha");
            d2.setValor("10.0");        
            itemDrinkRepository.save(d2);
 
        }
        
    }
    
    public List<String> recuperaOpcoesDrinks(){
        
        itemDrinkRepository = context.getBean(ItemDrinkRepository.class);        
        Iterable<ItemDrink> drinks = itemDrinkRepository.findAll();    
        List<String> result = new ArrayList<>();
        
        for(ItemDrink drink : drinks){
            result.add(drink.getNome());
        }
    
        return result;        
    }

    public List<String> recuperaValoresDrinks() {
        
        itemDrinkRepository = context.getBean(ItemDrinkRepository.class);        
        Iterable<ItemDrink> drinks = itemDrinkRepository.findAll();    
        List<String> result = new ArrayList<>();
        
        for(ItemDrink drink : drinks){
            result.add(drink.getValor());
        }
    
        return result;
    }
    
}
