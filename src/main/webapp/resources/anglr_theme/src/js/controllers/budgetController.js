app.controller("budgetController", ['$scope', 'JQ_CONFIG', '$log', '$modal', 'budgetService', function ($scope, JQ_CONFIG, $log, $modal, budgetService) {

    $scope.budget = {};

    $scope.getBudget = function () {
        budgetService.getBudget()
            .success(function (data, status, header) {
                $scope.budget = data;
            })
            .error(function (data, status, header) {
                $scope.form.message = "There was an error, please try again. (" + status + ")";
            });
    };

    $scope.addBudget = function () {
        budgetService.addBudget($scope.budget)
            .success(function (data, status, header) {
                $scope.form.$setPristine();

                $scope.budget = null;
                $scope.form.submitted = true;
                $scope.form.status = true;
                $scope.form.message = "Your budget has been saved.";
            })
            .error(function (data, status, header) {
                $scope.form.submitted = true;
                $scope.form.status = false;
                $scope.form.message = "There was an error, please try again. (" + status + ")";
            });
    };

}]);