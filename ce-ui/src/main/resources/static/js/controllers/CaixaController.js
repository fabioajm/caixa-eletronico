app.controller('CaixaController', function($scope, $routeParams, $http, toastr) {
    $scope.name = "CaixaController";
    $scope.params = $routeParams;
    $scope.caixas = [];
    $scope.dez  = {valor:10, quantidade:0};
    $scope.vinte  = {valor:20, quantidade:0};
    $scope.cinquenta  = {valor:50, quantidade:0};
    $scope.cem  = {valor:100, quantidade:0};
    var notas =[$scope.dez, $scope.vinte, $scope.cinquenta, $scope.cem];
    $scope.caixa = {nome:'', notas:notas};
    
    
    
    $scope.submit = function(){
    	$http.post('cecore/caixaeletronico', $scope.caixa).success(function(data) {
    		$scope.data = data;
    		toastr.sucess(err.message);
    	}).error(function(err){
    		toastr.error(err.message);
    	});
    };
    
    $scope.buscar = function(){
    	$http.get('cecore/caixaeletronico').success(function(data) {
    		$scope.caixas = data;
    	})
    	.error(function(err){
    		toastr.error(err.message);
    	});
    };
    reset = function(){
    	$scope.dez  = {valor:10, quantidade:0};
	    $scope.vinte  = {valor:20, quantidade:0};
	    $scope.cinquenta  = {valor:50, quantidade:0};
	    $scope.cem  = {valor:100, quantidade:0};
	    notas =[$scope.dez, $scope.vinte, $scope.cinquenta, $scope.cem];
	    $scope.caixa = {nome:'', notas:notas};
    };
    
});