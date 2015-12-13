app.service("transactionService", ['$http', '$q', function ($http, $q) {

    this.addTransaction = function (transaction) {

        return $http.post("/transaction/add", transaction);

    };

    this.loadTransactions = function (transaction) {

        return $http.get("/transaction/list");

    };

    this.deleteTransaction = function (id_transaction) {

        return $http.delete("/transaction/delete/" + id_transaction);

    };

    this.getTransaction = function (id_transaction) {

        return $http.get("/transaction/" + id_transaction);

    };


}])