package app;

import app.bot.dao.EspetinhoDAO;
import app.bot.dao.PorcaoDAO;
import app.bot.dao.CervejaDAO;
import app.bot.dao.DrinkDAO;
import app.bot.dao.NaoAlcoolDAO;
import app.bot.item.ItemEspetinho;
import app.bot.item.ItemPorcao;
import app.bot.item.ItemCerveja;
import app.bot.item.ItemEspetinhoRepository;
import app.bot.item.ItemPorcaoRepository;
import app.bot.item.ItemCervejaRepository;
import app.bot.item.ItemDrink;
import app.bot.item.ItemDrinkRepository;
import app.bot.item.ItemNaoAlcool;
import app.bot.item.ItemNaoAlcoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ControllerBar {
    
    @Autowired
    private ApplicationContext context;    
    private ItemEspetinhoRepository itemEspetinhoRepository;
    private ItemPorcaoRepository itemPorcaoRepository;
    private ItemCervejaRepository itemCervejaRepository;
    private ItemDrinkRepository itemDrinkRepository;
    private ItemNaoAlcoolRepository itemNaoAlcoolRepository;
    
    //Preencher Banco
    @RequestMapping(method=RequestMethod.POST, value="/salvaEspetinho")
    public ItemEspetinho preencheBanco(@RequestBody ItemEspetinho espetinho) {

        EspetinhoDAO bancoEspetinho = new EspetinhoDAO(context);
        bancoEspetinho.cadastraEspetinhos();
        
        System.out.println("uebaaaa Espetinho");
        itemEspetinhoRepository = context.getBean(ItemEspetinhoRepository.class);
        itemEspetinhoRepository.save(espetinho);

        return espetinho;

    }
    
    @RequestMapping(method=RequestMethod.POST, value="/salvaPorcao")
    public ItemPorcao preencheBanco(@RequestBody ItemPorcao porcao) {

        PorcaoDAO bancoPorcao = new PorcaoDAO(context);
        bancoPorcao.cadastraPorcoes();
        
        System.out.println("uebaaaa Porção");
        itemPorcaoRepository = context.getBean(ItemPorcaoRepository.class);
        itemPorcaoRepository.save(porcao);

        return porcao;

    }
   
    @RequestMapping(method=RequestMethod.POST, value="/salvaCerveja")
    public ItemCerveja preencheBanco(@RequestBody ItemCerveja cerveja) {

        CervejaDAO bancoCerveja = new CervejaDAO(context);
        bancoCerveja.cadastraCervejas();
        
        System.out.println("uebaaaa Cerveja");
        itemCervejaRepository = context.getBean(ItemCervejaRepository.class);
        itemCervejaRepository.save(cerveja);

        return cerveja;

    }
    
    @RequestMapping(method=RequestMethod.POST, value="/salvaDrink")
    public ItemDrink preencheBanco(@RequestBody ItemDrink drink) {

        DrinkDAO bancoDrink = new DrinkDAO(context);
        bancoDrink.cadastraDrink();
        
        System.out.println("uebaaaa Drink");
        itemDrinkRepository = context.getBean(ItemDrinkRepository.class);
        itemDrinkRepository.save(drink);

        return drink;

    }
    
    @RequestMapping(method=RequestMethod.POST, value="/salvaNaoAlcool")
    public ItemNaoAlcool preencheBanco(@RequestBody ItemNaoAlcool naoAlcool) {

        NaoAlcoolDAO bancoNaoAlcool = new NaoAlcoolDAO(context);
        bancoNaoAlcool.cadastraNaoAlcool();
        
        System.out.println("uebaaaa Nao Alcoólico");
        itemNaoAlcoolRepository = context.getBean(ItemNaoAlcoolRepository.class);
        itemNaoAlcoolRepository.save(naoAlcool);

        return naoAlcool;

    }
    
    //Listar Banco    
    @RequestMapping(method=RequestMethod.GET, value="/listaEspetinhos")
    public List<ItemEspetinho> listarEspetinhos() {

        System.out.println("uebaaa!!! Espetinhos voltaram");
        itemEspetinhoRepository = context.getBean(ItemEspetinhoRepository.class);

        return (List<ItemEspetinho>) itemEspetinhoRepository.findAll();

    }
    
    @RequestMapping(method=RequestMethod.GET, value="/listaPorcao")
    public List<ItemPorcao> listarPorcoes() {

        System.out.println("uebaaa!!! Porções voltaram");
        itemPorcaoRepository = context.getBean(ItemPorcaoRepository.class);

        return (List<ItemPorcao>) itemPorcaoRepository.findAll();

    }
    
    @RequestMapping(method=RequestMethod.GET, value="/listaCervejas")
    public List<ItemCerveja> listarCervejas() {

        System.out.println("uebaaa!!! Cervejas voltaram");
        itemCervejaRepository = context.getBean(ItemCervejaRepository.class);

        return (List<ItemCerveja>) itemCervejaRepository.findAll();

    }
    
    @RequestMapping(method=RequestMethod.GET, value="/listaDrinks")
    public List<ItemDrink> listarDrinks() {

        System.out.println("uebaaa!!! Drinks voltaram");
        itemDrinkRepository = context.getBean(ItemDrinkRepository.class);

        return (List<ItemDrink>) itemDrinkRepository.findAll();

    }
    
    @RequestMapping(method=RequestMethod.GET, value="/listaNaoAlcools")
    public List<ItemNaoAlcool> listarNaoAlcool(){

        System.out.println("uebaaa!!! Não Alcoólicos voltaram");
        itemNaoAlcoolRepository = context.getBean(ItemNaoAlcoolRepository.class);

        return (List<ItemNaoAlcool>) itemNaoAlcoolRepository.findAll();

    }
    
}