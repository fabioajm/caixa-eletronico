angular.module('hello', [ 'ngRoute' ])
  .controller('home', function($scope, $http) {
    
    $http.get('cecore/notas').success(function(data) {
        $scope.notas = data;
        console.log(data);
      });
});