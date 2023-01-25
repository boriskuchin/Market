angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1/products';
    const cartPath = 'http://localhost:8189/market/api/v1/cart';


    $scope.loadProducts = function () {
        $http({
            url: contextPath ,
            method: 'get',


        }).then(function (response) {

                $scope.ProductList = response.data;
            });
    };


    $scope.deleteProduct = function (productID) {
        $http({
            url: contextPath,
            method: 'DELETE',
            params: {
                id: productID
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    };


    // $scope.addProduct = function () {
    //     console.log($scope.newProduct);
    //     $http.post(contextPath, $scope.newProduct)
    //         .then(function (response) {
    //             $scope.loadProducts();
    //         });
    //     $scope.newProduct = null
    // }
    //
    //
    $scope.addToCart = function (productID) {
        $http({
            url: contextPath + "/add-to-cart",
            method: 'GET',
            params: {
                id: productID
            }
        }).then(function (response) {
            $scope.loadCart();
        });
    };


    $scope.loadCart = function () {
        $http({
            url: cartPath ,
            method: 'get',

        }).then(function (response) {
            $scope.CartList = response.data;
        });
    };


    $scope.loadProducts();
    $scope.loadCart();


});
