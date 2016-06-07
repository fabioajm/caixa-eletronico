app.controller('SaqueController', function($scope, $routeParams, $http, toastr) {
    $scope.name = "SaqueController";
    $scope.params = $routeParams;
    $scope.saque = {nomeCaixa:'', nomeUsuario:'', valor:''};
    $scope.notas = [];
    
    
    
    $scope.sacar = function(){
    	$http.get('cecore/caixaeletronico/'+ $scope.saque.nomeCaixa+'/sacar/'+ $scope.saque.nomeUsuario+'/'+ $scope.saque.valor)
    	.success(function(data) {
    		$scope.notas = data;
    	})
    	.error(function(err){
    	   toastr.error(err.message);
     	   console.log(err);
        });
    };
    
    $scope.buscar = function(){
    	$http.get('cecore/caixaeletronico').success(function(data) {
    		$scope.caixas = data;
    	});
    };
    
    
});