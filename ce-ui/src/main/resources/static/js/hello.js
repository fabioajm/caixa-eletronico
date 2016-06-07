angular.module('hello', ['ngRoute'])

 .controller('MainController', function($scope, $route, $routeParams, $location) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
    
 })

 .controller('UsuarioController', function($scope, $routeParams) {
     $scope.name = "UsuarioController";
     $scope.params = $routeParams;
 })

 .controller('CaixaController', function($scope, $routeParams, $http) {
     $scope.name = "CaixaController";
     $scope.params = $routeParams;
     $http.get('cecore/notas').success(function(data) {
         $scope.notas = data;
         console.log(data);
       });
 })
 .controller('SaqueController', function($scope, $routeParams) {
     $scope.name = "SaqueController";
     $scope.params = $routeParams;
 })

.config(function($routeProvider, $locationProvider) {
  $routeProvider
   .when('/usuarios', {
    templateUrl: 'views/usuario/cadastrar.html',
    controller: 'UsuarioController',
   
  })
  .when('/usuarios/consultar', {
    templateUrl: '/views/usuario/consultar.html',
    controller: 'UsuarioController',
   
  })
  .when('/caixas', {
    templateUrl: 'views/caixa/cadastrar.html',
    controller: 'CaixaController'
  })
  .when('/caixas/consultar', {
    templateUrl: '/views/caixa/consultar.html',
    controller: 'CaixaController',
   
  })
  .when('/home', {
    templateUrl: 'views/home.html',
    controller: 'MainController'
  })
  .when('/saque', {
    templateUrl: 'views/caixa/sacar.html',
    controller: 'SaqueController'
  })
  .otherwise({
      redirectTo: '/home'
    });

  // configure html5 to get links working on jsfiddle
  $locationProvider.html5Mode({
	  enabled: true,
	  requireBase: false
	});
});