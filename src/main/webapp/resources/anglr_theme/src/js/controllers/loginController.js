angular.module('app').controller("loginController", ['$scope', '$rootScope', '$location', '$http', '$localStorage', function ($scope, $rootScope, $location, $http, $localStorage) {


    $scope.credentials = null;

    $scope.credentials = {};
    $scope.login = function () {

        $http({
            method: "POST",
            data: $scope.credentials,
            url: '/api/login'
        })
            .success(function (result, status, headers) {
                $scope.authError = null;
                $rootScope.authenticated = true;

                setCredentials(headers('X-AUTH-TOKEN'));
                $location.path("/");

            }).error(function (result, status, headers) {
                $scope.authError = "Bad Credentials";
            });

    };

    function setCredentials(token) {
        $http.defaults.headers.common['X-AUTH-TOKEN'] = token;
        $rootScope.currentUser = {token: token}
        $localStorage.currentUser = $rootScope.currentUser;

    }


}])