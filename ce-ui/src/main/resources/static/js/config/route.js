app.config(function($routeProvider, $locationProvider) {
	  $routeProvider
	   .when('/usuarios', {
	    templateUrl: '/views/usuario/cadastrar.html',
	    controller: 'UsuarioController',
	   
	  })
	  .when('/usuarios/consultar', {
	    templateUrl: '/views/usuario/consultar.html',
	    controller: 'UsuarioController',
	   
	  })
	  .when('/caixas', {
	    templateUrl: '/views/caixa/cadastrar.html',
	    controller: 'CaixaController'
	  })
	  .when('/caixas/consultar', {
	    templateUrl: '/views/caixa/consultar.html',
	    controller: 'CaixaController',
	   
	  })
	  .when('/home', {
	    templateUrl: '/views/home.html',
	    controller: 'MainController'
	  })
	  .when('/saque', {
	    templateUrl: '/views/caixa/sacar.html',
	    controller: 'SaqueController'
	  })
	  .otherwise({
	      redirectTo: '/home'
	    });

	  // configure html5 to get links working on jsfiddle
	/*  $locationProvider.html5Mode({
		  enabled: true,
		  requireBase: false
		});*/
	});