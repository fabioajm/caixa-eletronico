angular.module('hello', [ 'ngRoute' ])
  .controller('home', function($scope, $http) {
//    $scope.notas = {id: 'xxx', content: 'Hello World!'}
    
    $http.get('cecore/notas').success(function(data) {
        $scope.notas = data;
        console.log(data);
      });
});