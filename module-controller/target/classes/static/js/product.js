myApp.factory('Products', function($resource){
    return $resource('/api/v1/product');
});