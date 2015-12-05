var mod = angular.module('hello', []);
    mod.controller('home', function($scope, $http) {
        $http.get('/resource/').success(function(data) {
            $scope.greeting = data;
        })
    });
    mod.controller('notification', function($scope, $http, $timeout) {

        $scope.getNotification = function() {

            $http.get('/notification/current/').success(function (data) {
                $scope.notification = data;
            });
        };

        $scope.intervalFunction = function() {
            $timeout(function() {
                $scope.getNotification();
                $scope.intervalFunction();
            }, 6000);
        };

        $scope.getNotification();
        $scope.intervalFunction();
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
            }, 6000);
        };

        $scope.getImage();
        $scope.intervalFunction2();
    });