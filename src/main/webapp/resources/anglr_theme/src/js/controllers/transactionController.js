app.controller("transactionController", ['$scope', '$stateParams', '$location', 'JQ_CONFIG', '$log', '$modal', 'transactionService',
    function ($scope, $stateParams, $location, JQ_CONFIG, $log, $modal, transactionService) {

    $scope.transaction = {};
    $scope.transactions = [];
    $scope.type = $stateParams.type;

    if ($stateParams.id !== undefined) {
        transactionService.getTransaction($stateParams.id)
            .success(function (data, status, header) {
                $scope.transaction = data;
                $scope.transaction.date = new Date(data.date);
                $scope.transaction.category_name = data.category.name;
                $scope.transaction.id_category = data.category.id_category;
            })
            .error(function (data, status, header) {
            });
    }


    $scope.openCategoriesPopup = function () {
        modalInstance = $modal.open({
            templateUrl: JQ_CONFIG.SOURCE_PATH + '/tpl/category/category.html',
            controller: 'categoryController',
            size: "lg"
        });

        modalInstance.result.then(function (subcategory) {
                $scope.transaction.id_category = subcategory.id_category;
                $scope.transaction.category_name = subcategory.name;
            }
        );
    }

    $scope.loadTransactions = function () {
        transactionService.loadTransactions()
            .success(function (data, status, header) {
                $scope.transactions = data;
            })
            .error(function (data, status, header) {
            });
    }

    $scope.addTransaction = function () {
        console.log($scope.transaction);
        if ($stateParams.id === undefined) {
            if ($scope.type === 'income')
                $scope.transaction.type = 'INCOME_TRANSACTION';
            if ($scope.type === 'expense')
                $scope.transaction.type = 'EXPENSE_TRANSACTION';
        }
        transactionService.addTransaction($scope.transaction)
            .success(function (data, status, header) {
                if ($scope.transaction.id_transaction !== undefined)
                    $location.path("app/transaction/list");
                $scope.form.$setPristine();

                $scope.transaction = null;
                $scope.form.submitted = true;
                $scope.form.status = true;
                $scope.form.message = "Your transaction has been saved.";
            })
            .error(function (data, status, header) {
                $scope.form.submitted = true;
                $scope.form.status = false;
                $scope.form.message = "There was an error, please try again. "+data.message+" (" + status + ")";
            });
    };

    $scope.deleteTransaction = function (id_transaction) {
        transactionService.deleteTransaction(id_transaction)
            .success(function (data, status, header) {
                $scope.loadTransactions();
            })
            .error(function (data, status, header) {
                $scope.form.message = "There was an error, please try again. (" + status + ")";
            });
    }

}])