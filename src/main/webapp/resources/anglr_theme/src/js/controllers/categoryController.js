app.controller("categoryController", ['$scope', '$location', '$state', '$modalInstance', 'categoryService', function ($scope, $location, $state, $modalInstance, categoryService) {

	$scope.categories=[];
	$scope.subcategories=[];
	
    $scope.showCategory = true;
    
    $scope.showSubcategory = false;

    $scope.back = function () {
        $scope.showCategory = true;
        $scope.showSubcategory = false;

    }

    $scope.loadCategories = function () {
        categoryService.getCategories()
            .success(function (data, status, header) {
                $scope.categories = data;
            })
            .error(function (data, status, header) {
                alert("ERROR" + status)
            })
    }

    $scope.loadCategories();


    $scope.selectCategory = function (id_category) {

        //switch panels
        $scope.showCategory = false;
        $scope.showSubcategory = true;
        $scope.id_parent=id_category;
        
        categoryService.getSubcategories(id_category)
            .success(function (data, status, header) {
                $scope.subcategories = data;
            })
            .error(function (data, status, header) {
                alert("ERROR" + status)
            })
    }


    $scope.selectSubCategory = function (category) {
        $modalInstance.close({"id_category": category.id_category, "name": category.name});
    }

    
    $scope.addCategory=function(){
    	
    	 categoryService.addCategory({name:$scope.new_category})
         .success(function (data, status, header) {
        	 $scope.categories.push(data);
        	 $scope.new_category="";
         })
         .error(function (data, status, header) {
             alert("ERROR" + status)
         })
    
    }
    
    $scope.addSubcategory=function(id){
   	 categoryService.addCategory({name:$scope.new_subcategory,id_parent:$scope.id_parent})
        .success(function (data, status, header) {
        	 $scope.subcategories.push(data);
        	 $scope.new_subcategory="";
        })
        .error(function (data, status, header) {
            alert("ERROR" + status)
        })
   
   }
    
    $scope.deleteCategory=function(id){
    	
    	console.log("deleting category width id:"+id);
    	
    	categoryService.deleteCategory({id_category:id})
    	.success(function(data,status,header){
    		//update the ui
    		 $scope.loadCategories();
    	})
    	.error(function(data,status,header){
    		 alert("ERROR" + status)
    	})
    }
    
    
    
    $scope.deleteSubcategory=function(id){
    	
    	console.log("deleting category width id:"+id);
    	
    	categoryService.deleteCategory({id_category:id})
    	.success(function(data,status,header){
    		/**
    		 * TODO: remove the category from scope.array instead of hitting the database again. 
    		 */
    		 categoryService.getSubcategories(id)
             .success(function (data, status, header) {
                 $scope.subcategories = data;
             })
             .error(function (data, status, header) {
                 alert("ERROR" + status)
             })
             
    	})
    	.error(function(data,status,header){
    		 alert("ERROR" + status)
    	})
    }

}])