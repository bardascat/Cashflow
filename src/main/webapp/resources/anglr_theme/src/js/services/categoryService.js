angular.module('app').service("categoryService", ['$http', '$q', function ($http, $q) {

    this.getCategories = function () {
        return $http.get("/category/get-categories");
    };

    this.getSubcategories = function (id_category) {
        return $http.get("/category/get-categories/", {
            params: {
                id_parent: id_category
            }
        });
    };

    
    this.addCategory = function (category) {
        return $http(
        		{
        			method:"POST",
        			data:category,
        			url:"/category/add-category/",
        			//headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        		}
        );
    };
    
    
    this.deleteCategory = function (category) {
        return $http(
        		{
        			method:"POST",
        			data:category,
        			url:"/category/delete-category/"
        		}
        );
    };

}])