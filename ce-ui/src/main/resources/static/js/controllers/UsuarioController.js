app.controller('UsuarioController', function($scope, $routeParams, $http, toastr) {
	var self = this;
	$scope.usuario = {nome:'test', saldo:200};
    $scope.name = "UsuarioController";
    $scope.params = $routeParams;
    $scope.q ='';
    $scope.usuarios = [];
    
    
    $scope.submit = function() {
    	$http.post('cecrud/usuarios',$scope.usuario).success(function(data) {
            $scope.notas = data;
            console.log(data);
            reset();
          })
          .error(function(err){
        	  toastr.error(err.message);
        	   console.log(err);
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