var app = angular.module("botapp",['ngRoute']);

app.controller("botctrl", function($scope, $http){
       
    $scope.enviar = function(texto){
        $scope.enviando=true;
              
        var update = {};
        update.message = {};
        update.message.from = {};
        update.message.from.id = 1;
        update.message.text = texto;
        
        
        $http.post("http://localhost:8080/update", update)
            .then(function(result){
                console.log('result');
                console.log(result);
                $scope.enviando=false;
                $scope.mensagem="Enviado!";
                $scope.resposta = result;
                console.log(result);
            },function(erro){
                console.log('erroo');
                console.log(erro);
                $scope.enviando=false;
                $scope.mensagem="Erro no Envio!";
                console.log(erro);
            });
        console.log("foi!");
    }
    
    //Módulo de Rotas
    app.config(function($routeProvider){
        $routeProvider
            .when('/cadastro', {
                templateUrl: 'html/cadastro.html'
            })
            .when('/listar', {
                templateUrl: 'html/listar.html', 
            })
            .otherwise({
                templateUrl: 'html/home.html'
            });
    });
    
    $scope.itens = [];
    $scope.categorias = [
        {nome: "Espetinho", tipo: "Alimentos"},
        {nome: "Porção", tipo: "Alimentos"},
        {nome: "Cerveja", tipo: "Bebidas"},
        {nome: "Drink", tipo: "Bebidas"},
        {nome: "Não Alcoólico", tipo: "Bebidas"}
    ];
    //CRUD
    $scope.adcionarItem = function (item) {
        delete $scope.item;
        $scope.enviando=true;
        console.log("savandoooo");
        console.log(item);                    
        
        if(item.categoria.nome === "Espetinho"){
            console.log("Agora foi no espetinho né?");
            $http.post("https://projeto-lab-chopp.herokuapp.com/salvaEspetinho", item)
            .then(function(){
                console.log("foieee");
                $scope.enviando=false;
                $scope.mensagem="Espetinho cadastrado com sucesso!";
            }, function(){
                console.log("erro");
                $scope.enviando=false;
                $scope.mensagem="Erro no cadastro!";
            });
        }
        if(item.categoria.nome === "Porção"){
            console.log("Porção agora?");
            $http.post("https://projeto-lab-chopp.herokuapp.com/salvaPorcao", item)
            .then(function(){
                console.log("foieee");
                $scope.enviando=false;
                $scope.mensagem="Porção cadastrada com sucesso!";
            }, function(){
                console.log("erro");
                $scope.enviando=false;
                $scope.mensagem="Erro no cadastro!";
            });
        }
        if(item.categoria.nome === "Cerveja"){
            console.log("Escolheu a cerveja, miseráve!");
            $http.post("https://projeto-lab-chopp.herokuapp.com/salvaCerveja", item)
            .then(function(){
                console.log("foieee");
                $scope.enviando=false;
                $scope.mensagem="Cerveja cadastrada com sucesso!";
            }, function(){
                console.log("erro");
                $scope.enviando=false;
                $scope.mensagem="Erro no cadastro!";
            });
        }
        if(item.categoria.nome === "Drink"){
            console.log("Escolheu drink, miseráve!");
            $http.post("https://projeto-lab-chopp.herokuapp.com/salvaDrink", item)
            .then(function(){
                console.log("foieee");
                $scope.enviando=false;
                $scope.mensagem="Drink cadastrada com sucesso!";
            }, function(){
                console.log("erro");
                $scope.enviando=false;
                $scope.mensagem="Erro no cadastro!";
            });
        }
        if(item.categoria.nome === "Não Alcoólico"){
            console.log("Escolheu Não Alcoolico é, arregou?");
            $http.post("https://projeto-lab-chopp.herokuapp.com/salvaNaoAlcool", item)
            .then(function(){
                console.log("foieee");
                $scope.enviando=false;
                $scope.mensagem="Bebida não alcoólica cadastrada com sucesso!";
            }, function(){
                console.log("erro");
                $scope.enviando=false;
                $scope.mensagem="Erro no cadastro!";
            });
        }  
    
    }    
    $scope.listarItem = function(item){
        $scope.buscando = true;
        console.log("buscandooOOOoo");        
        if(item.categoria.nome === "Espetinho"){
            $http.get("https://projeto-lab-chopp.herokuapp.com/listaEspetinhos")
                .then(function(item){
                    $scope.buscando=false;
                    console.log(item);
                    $scope.itens = item.data;
                },function(erro){
                    $scope.buscando=false;
                    console.log(erro);
                });
        }
        if(item.categoria.nome === "Porção"){
            $http.get("https://projeto-lab-chopp.herokuapp.com/listaPorcao")
                .then(function(item){
                    $scope.buscando=false;
                    console.log(item);
                    $scope.itens = item.data;
                },function(erro){
                    $scope.buscando=false;
                    console.log(erro);
                });
        }
        if(item.categoria.nome === "Cerveja"){
            $http.get("https://projeto-lab-chopp.herokuapp.com/listaCervejas")
                .then(function(item){
                    $scope.buscando=false;
                    console.log(item);
                    $scope.itens = item.data;
                },function(erro){
                    $scope.buscando=false;
                    console.log(erro);
                });
        }
        if(item.categoria.nome === "Drink"){
            $http.get("https://projeto-lab-chopp.herokuapp.com/listaDrinks")
                .then(function(item){
                    $scope.buscando=false;
                    console.log(item);
                    $scope.itens = item.data;
                },function(erro){
                    $scope.buscando=false;
                    console.log(erro);
                });
        }
        if(item.categoria.nome === "Não Alcoólico"){
            $http.get("https://projeto-lab-chopp.herokuapp.com/listaNaoAlcools")
                .then(function(item){
                    $scope.buscando=false;
                    console.log(item);
                    $scope.itens = item.data;
                },function(erro){
                    $scope.buscando=false;
                    console.log(erro);
                },function(erro){
                    $scope.buscando=false;
                    console.log(erro);
                });
        }

    }
    $scope.deletarItem = function(item){
        $scope.enviando = true;
        console.log("Deletandoooo");
        console.log(item);
               
            $http.post("https://projeto-lab-chopp.herokuapp.com/deletarItens/{item.id}", item.id)
                .then(function(){
                    console.log("Deletou");
                    $scope.enviando=false;
                    $scope.mensagem="Deletou!";
                },function(){
                    console.log("Erro");
                    $scope.enviando=false;
                    $scope.mensagem="Não deletou!";
                });
        
    }
    
    
    $scope.clientes = [];    
    $scope.relatorios = [
        {modelo: "Consumo Médio de Clientes"},
        {modelo: "Avaliação dos Clientes"},
        {modelo: "Itens mais pedidos"}
    ];
    
    //RELATÓRIOS
    
    $scope.listarClientes = function(cliente){
        $scope.buscando = true;
        console.log("latório"); 
        if(cliente.relatorio.modelo === "Consumo Médio de Clientes"){
            console.log("FOI!!!, CONSUMO MÉDIO");
            $scope.nome = "Nome";
            $scope.propriedade = "Consumo Médio";
            
            $http.get("http://localhost:8080/clientes")
                .then(function(cliente){
                    console.log('Vaaaai!');
                    $scope.buscando=false;
                    
                    $scope.nomes = [];
                    for(i = 0; i < cliente.data.length; i++){
                       $scope.nomes.push(cliente.data[i].id);
                        
                    }
                    console.log($scope.nomes);
                    $scope.consumos = [];
                    for(i = 0; i < cliente.data.length; i++){
                       $scope.consumos.push(cliente.data[i].categoria);
                        
                    }
                    console.log($scope.consumos);                 

                },function(erro){
                    $scope.buscando=false;
                    console.log(erro);
                });
        }
        if(cliente.relatorio.modelo === "Avaliação dos Clientes"){
            console.log("FOI!!!, AVALIAÇÃO")
            $scope.nome = "Nome";
            $scope.propriedade = "Nota da Avaliação";
            $scope.primeiroNome = cliente.first_name;
            $scope.operacao = cliente.avaliacao;
            $http.get("http://localhost:8080/clientes")
                .then(function(cliente){
                    console.log('Vaaaai!');
                    console.log(cliente);
                    $scope.buscando=false;
                    console.log(cliente);
                    $scope.clientes = cliente.data;
                },function(erro){
                    $scope.buscando=false;
                    console.log(erro);
                });
        }
        if(cliente.relatorio.modelo === "Itens mais pedidos"){
            console.log("FOI!!!, MAIS PEDIDOS")
            $scope.nome = "Nome";
            $scope.propriedade = "Nota da Avaliação";
            $http.get("http://localhost:8080/clientes")
                .then(function(cliente){
                    console.log('Vaaaai!');
                    console.log(cliente);
                    $scope.buscando=false;
                    console.log(cliente);
                    $scope.clientes = cliente.data;
                },function(erro){
                    $scope.buscando=false;
                    console.log(erro);
                });
        }
    }
    
});