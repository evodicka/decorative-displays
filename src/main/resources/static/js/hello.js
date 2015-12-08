var mod = angular.module('hello', ['ngAnimate']);
    mod.controller('notification', function($scope, $http, $timeout) {
        $scope.visible = false;
        $scope.getNotification = function() {

            $http.get('/notification/current/').success(function (data) {
                $scope.notification = data;
            });
        };

        $scope.intervalFunction = function() {
            $timeout(function() {
                $scope.visible = false;
                setTimeout(function() {
                    $scope.getNotification();
                    $scope.intervalFunction();
                    $scope.visible = true;
                }, 2000);
            }, 10000);
        };

        $scope.getNotification();
        $scope.intervalFunction();
    });
    mod.controller('clock', function($scope, $timeout) {
        $scope.setClock = function() {
            $timeout(function() {
                $scope.CurrentDate = new Date();
                $scope.setClock();
            }, 30000);
        };

        $scope.setClock();
        $scope.CurrentDate = new Date();
    });
    mod.controller('images', function($scope, $http, $timeout) {
        $scope.getImage = function() {

            $http.get('/images/current/').success(function (data) {
                $scope.not = data;
            });
        };

        $scope.intervalFunction2 = function() {
            $timeout(function() {
                $scope.getImage();
                $scope.intervalFunction2();
            }, 60000);
        };

        $scope.getImage();
        $scope.intervalFunction2();
    });