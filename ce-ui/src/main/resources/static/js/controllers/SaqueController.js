app.controller('SaqueController', function($scope, $routeParams, $http, toastr) {
    $scope.name = "SaqueController";
    $scope.params = $routeParams;
    $scope.saque = {nomeCaixa:'', idUsuario:'', valor:''};
    $scope.notas = [];
    
    
    
    $scope.sacar = function(){
    	$http.get('cecore/caixaeletronico/'+ $scope.saque.nomeCaixa+'/sacar/'+ $scope.saque.idUsuario+'/'+ $scope.saque.valor)
    	.success(function(data) {
    		$scope.notas = data;
    	})
    	.error(function(err){
    	   toastr.error(err.message);
        });
    };
    
     var buscarCaixas = function(){
    	$http.get('cecore/caixaeletronico').success(function(data) {
    		$scope.caixas = data;
    	})
    	.error(function(err){
    		toastr.error(err.message);
    	});
    };
    
    var buscarUsuarios = function(){
    	$http.get('cecrud/usuarios').success(function(data) {
    		$scope.usuarios = data;
    	})
    	.error(function(err){
    		toastr.error(err.message);
    	});
    };
    
    buscarCaixas();
    buscarUsuarios();
    
});