var myApp = angular.module('ProductApp', ['ngResource']);

myApp.config(function($resourceProvider){
    $resourceProvider.defaults.stripTrailingSlashes = false;
});

myApp.controller('ProductCtrl', function($scope, Products){

    console.log('Thymeleaf AngularJs');

    $scope.name = "";

    $scope.products = Products.query();

});