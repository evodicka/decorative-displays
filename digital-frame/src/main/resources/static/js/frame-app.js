var mod = angular.module('frame', ['ngAnimate']);

mod.controller('notification', function ($scope, $http, $timeout) {
    $scope.visible = false;
    $scope.getNotification = function () {

        $http.get('/notification/current/').success(function (data) {
            $scope.notification = data;
        });
    };

    $scope.intervalNotification = function () {
        $timeout(function () {
            $scope.visible = false;
            setTimeout(function () {
                $scope.getNotification();
                $scope.intervalNotification();
                $scope.visible = true;
            }, 2000);
        }, 10000);
    };

    $scope.getNotification();
    $scope.intervalNotification();
});

mod.controller('clock', function ($scope, $timeout) {
    $scope.setClock = function () {
        $timeout(function () {
            $scope.CurrentDate = new Date();
            $scope.setClock();
        }, 10000);
    };

    $scope.setClock();
    $scope.CurrentDate = new Date();
});

mod.controller('images', function ($scope, $http, $timeout) {
    $scope.getImage = function () {

        $http.get('/images/current/').success(function (data) {
            $scope.image = data;
        });
    };

    $scope.intervalImage = function () {
        $timeout(function () {
            $scope.getImage();
            $scope.intervalImage();
        }, 60000);
    };

    $scope.getImage();
    $scope.intervalImage();
});