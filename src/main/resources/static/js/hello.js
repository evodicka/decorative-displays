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
            }, 1000);
        };

        $scope.intervalFunction();
    });