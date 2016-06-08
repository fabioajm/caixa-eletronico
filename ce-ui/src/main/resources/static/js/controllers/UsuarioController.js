app.controller('UsuarioController', function($scope, $routeParams, $http, toastr) {
	var self = this;
	$scope.usuario = {nome:'', saldo:0};
    $scope.name = "UsuarioController";
    $scope.params = $routeParams;
    $scope.q ='';
    $scope.usuarios = [];
    
    
    $scope.submit = function() {
    	$http.post('cecrud/usuarios',$scope.usuario).success(function(data) {
            $scope.notas = data;
            reset();
            toastr.sucess("Sucesso!");
          })
          .error(function(err){
        	  toastr.error(err.message);
          });
    };
    
    $scope.search = function(){
    	$http.get('cecrud/usuarios?q=' + $scope.q).success(function(data) {
    		$scope.usuarios = data;
    	})
    	.error(function(err){
    		toastr.error(err.message);
        	   console.log(err);
          });
    };
    
    
    reset = function(){
    	$scope.usuario = {nome:'', saldo:''};
    };
    
});