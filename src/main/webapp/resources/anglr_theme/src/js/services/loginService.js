angular.module('app').service("loginService", ['$http', '$q', function ($http, $q) {

    this.login = function (data) {


        return $http.post("/login.do", $.param($scope.data), {
            headers: {

                "content-type": "application/x-www-form-urlencoded"

            }
        });
    };

    this.getCsrfToken = function () {

        // var deffered = $q.defer();

        $http.get("/public/getCsrfToken.do")
            .success(function (data, status, headers) {
                deffered.resolve(data);

            })
            .error(function (data, status, headers) {

                deffered.reject(status);
            })

        return deffered.promise;
    }

}])