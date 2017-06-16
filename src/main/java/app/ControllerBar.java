package app;

import app.bot.dao.EspetinhoDAO;
import app.bot.dao.PorcaoDAO;
import app.bot.dao.CervejaDAO;
import app.bot.dao.DrinkDAO;
import app.bot.dao.NaoAlcoolDAO;
import app.bot.cardapio.ItemEspetinho;
import app.bot.cardapio.ItemPorcao;
import app.bot.cardapio.ItemCerveja;
import app.bot.cardapio.ItemEspetinhoRepository;
import app.bot.cardapio.ItemPorcaoRepository;
import app.bot.cardapio.ItemCervejaRepository;
import app.bot.cardapio.ItemDrink;
import app.bot.cardapio.ItemDrinkRepository;
import app.bot.cardapio.ItemNaoAlcool;
import app.bot.cardapio.ItemNaoAlcoolRepository;
import app.bot.cliente.Cliente;
import app.bot.cliente.ClienteRepository;
import app.bot.comanda.Comanda;
import app.bot.comanda.ComandaRepository;
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
    @Autowired
    private ItemEspetinhoRepository itemEspetinhoRepository;
    private ItemPorcaoRepository itemPorcaoRepository;
    private ItemCervejaRepository itemCervejaRepository;
    private ItemDrinkRepository itemDrinkRepository;
    private ItemNaoAlcoolRepository itemNaoAlcoolRepository;
    private ClienteRepository clienteRepository;
    //private ComandaRepository comandaRepository;
    
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
    
    //Listar Itens    
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
    
    //Alterar Itens
    @RequestMapping(method=RequestMethod.DELETE, value="/deletarItens/{id}")
    public void deletarItens(ItemEspetinho item) {

        System.out.println("uebaaa!!! Deletando");
        itemEspetinhoRepository = context.getBean(ItemEspetinhoRepository.class);

        itemEspetinhoRepository.delete(item.getId());

    }
 
    //CLIENTE
    @RequestMapping(method=RequestMethod.GET, value="/clientes")
    public List<Cliente> listarClientes(){
        
        clienteRepository = context.getBean(ClienteRepository.class);
        
        return (List<Cliente>) clienteRepository.findAll();
        
    }
    
    //COMANDA
    /*@RequestMapping(method=RequestMethod.GET, value="/comandas")
    public List<Comanda> listarComandas(Comanda comanda){
        
        comandaRepository = context.getBean(ComandaRepository.class);
        
        return (List<Comanda>) comandaRepository.findOne(comanda.id);
        
    }*/
    
}