angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage, $rootScope) {
    const contextPath = 'http://localhost:8189/market/api/v1/products';
    const orderPath = 'http://localhost:8191/market-orders/api/v1/order';
    const cartPath = 'http://localhost:8190/market-carts/api/v1/cart';
    const authPath = 'http://localhost:8193/market-auth/api/v1/auth'

    $scope.createOrder = function () {
        alert($scope.user.username);
        $http({
            url: orderPath + '/' + $scope.user.username,

            method: 'POST',
        }).then(function (response) {
            alert("Заказ создан")
            $scope.loadCart();
        });
    };



    if ($localStorage.springWebUser) {
        try {
            let jwt = $localStorage.springWebUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.springWebUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }


        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

    console.log($localStorage.springWebUser)

    $scope.loadProducts = function () {
        $http({
            url: contextPath ,
            method: 'get',


        }).then(function (response) {
            console.log(response.data);
            $scope.ProductList = response.data;
        });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        // $route.reload()
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
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

    $scope.tryToAuth = function () {
        $http.post( authPath + '/token', $scope.user)
            .then(function successCallback(response) {

                if (response.data.token) {
                    console.log(response.data)
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    // $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.addToCart = function (productID) {
        $http({
            url: cartPath + "/add/" + productID,
            method: 'GET',
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.deceraseProductQuantity = function (productID) {
        $http({
            url: cartPath + "/decrease/" + productID,
            method: 'GET',
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.removeProductFromCart = function (productID) {
        $http({
            url: cartPath + "/remove/" + productID,
            method: 'GET',
        }).then(function (response) {
            $scope.loadCart();
        });
    };



    $scope.loadCart = function () {
        $http({
            url: cartPath ,
            method: 'get',

        }).then(function (response) {
            console.log(response.data)
            $scope.cart = response.data;
        });
    }

    $scope.clearCart = function () {
        $http({
            url: cartPath +  "/clear",
            method: 'get',

        }).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.loadProducts();
    $scope.loadCart();

});