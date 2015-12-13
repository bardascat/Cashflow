app.service("reportService", ['$http', '$q','JQ_CONFIG','utilService',function ($http, $q,JQ_CONFIG,utilService) {

    this.getHighLevelStats = function () {
        return $http({
        	url:JQ_CONFIG.API_URL+"/dashboard/getHighLevelStats",
        	method:"GET"
        });

    };
    
    this.getBudgetStats=function(){
    	
    	return $http({
    		url:JQ_CONFIG.API_URL+"/dashboard/getBudgetStats",
    		method:"GET"
    	});
    }

    this.getCategoriesChartData=function(minDate,maxDate){
    
    	return $http({
    		url:JQ_CONFIG.API_URL+"/dashboard/getCategoriesChartData",
    		method:"GET",
    		params:{min:utilService.formattedDate(minDate),max:utilService.formattedDate(maxDate)}
    		
    		
    	});
    	
    }
   

}]);