app.service("budgetService", ['$http', '$q', function ($http, $q) {

    this.addBudget = function (budget) {

        return $http.post("/budget/add", budget);

    };

    this.getBudget = function () {

        return $http.get("/budget");

    };

}]);