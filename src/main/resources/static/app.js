var app = angular.module("botapp",[]);

app.controller("botctrl", function($scope, $http){
       
    $scope.enviar = function(texto){
        $scope.enviando=true;
              
        var update = {};
        update.message = {};
        update.message.from = {};
        update.message.from.id = 1;
        update.message.text = texto;
        
        
        $http.post("http://localhost:8080/update",update)
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
    
    $scope.itens = [];
    $scope.categorias = [
        {nome: "Espetinho", tipo: "Alimentos"},
        {nome: "Porção", tipo: "Alimentos"},
        {nome: "Cerveja", tipo: "Bebidas"},
        {nome: "Drink", tipo: "Bebidas"},
        {nome: "Não Alcoólico", tipo: "Bebidas"}
    ];
    
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
    $scope.listar = function(item){
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
                });
        }
    }  
    
});