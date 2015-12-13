'use strict';

/* Controllers */

app
  .controller('dashboardController', ['$scope','reportService', function($scope,reportService) {
	 
	  $scope.budgetStats={};
	  
	  
	  var loadData=function(){
		  getHighLevelStats();
		  getBudgetStats();
		  getCategoriesChartData();
	  }
	  
	  var getHighLevelStats=function(){
		  reportService.getHighLevelStats()
		  .success(function(data,status){
			  $scope.highLevelStats=data;
		  })
		  .error(function(data,status){
			  alert("Error");
		  });
	  }
	  
	  var getBudgetStats=function(){
		  
		  reportService.getBudgetStats()
		  .success(function(data,status){
			  $scope.budgetStats=data;
		  })
		  .error(function(data,status){
			  alert("Error - getBudgetStats");
		  });
		  
	  }
	  
	  var getCategoriesChartData=function(){
		  var date = new Date();
		  var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
		  var lastDay = new Date(date.getFullYear(), date.getMonth()+ 1, 0);
		
		  
		  console.log(firstDay);
		  console.log(lastDay);
		
		  reportService.getCategoriesChartData(firstDay,lastDay)
		  .success(function(data,status){
			console.log(data);  
		  })
		  .error(function(data,status){
			  console.log(data);  
		  });
		  
	  }
	  loadData();
	 
	  /**
	   * what's below is theme bullshit
	   */
	 $scope.month={};
	  
	 $scope.month.data='[{data: [[0, 460],[1, 800],[2, 25],[3, 224],[4, 13],[5, 318],[6, 318]], color: "#5482FF" }],{series: {bars: {show: true,},valueLabels: {show: true,showAsHtml: true,align: "center"}   },bars: {            align: "center",barWidth: 0.5},xaxis: {axisLabel: "World Cities",axisLabelUseCanvas: true,axisLabelFontSizePixels: 12,axisLabelPadding: 10,ticks: [[0, "Mancare"], [1, "Chirie"], [2, "Haine"], [3, "Others"],[4, "Pisica"], [5, "Sex"],[6, "Sex2"]]},yaxis: {axisLabel: "lei",axisLabelUseCanvas: true,axisLabelFontSizePixels: 12,axisLabelFontFamily: "Verdana, Arial",axisLabelPadding: 3},legend: {noColumns: 0,labelBoxBorderColor: "#000000",position: "nw"},grid: {hoverable: true,borderWidth: 2,backgroundColor: { colors: ["#ffffff", "#EDF5FF"] }}}';
     
    $scope.d = [ [1,6.5],[2,6.5],[3,7],[4,8],[5,7.5],[6,7],[7,6.8],[8,7],[9,7.2],[10,7],[11,6.8],[12,7] ];

    $scope.d0_1 = [ [0,7],[1,6.5],[2,12.5],[3,7],[4,9],[5,6],[6,11],[7,6.5],[8,8],[9,7] ];

    $scope.d0_2 = [ [0,4],[1,4.5],[2,7],[3,4.5],[4,3],[5,3.5],[6,6],[7,3],[8,4],[9,3] ];

    $scope.d1_1 = [ [10, 120], [20, 70], [30, 70], [40, 60] ];

    $scope.d1_2 = [ [10, 50],  [20, 60], [30, 90],  [40, 35] ];

    $scope.d1_3 = [ [10, 80],  [20, 40], [30, 30],  [40, 20] ];

    $scope.d2 = [];

    for (var i = 0; i < 20; ++i) {
      $scope.d2.push([i, Math.round( Math.sin(i)*100)/100] );
    }   

    $scope.d3 = [ 
      { label: "iPhone5S", data: 40 }, 
      { label: "iPad Mini", data: 10 },
      { label: "iPad Mini Retina", data: 20 },
      { label: "iPhone4S", data: 12 },
      { label: "iPad Air", data: 18 }
    ];

    $scope.refreshData = function(){
      $scope.d0_1 = $scope.d0_2;
    };

    $scope.getRandomData = function() {
      var data = [],
      totalPoints = 150;
      if (data.length > 0)
        data = data.slice(1);
      while (data.length < totalPoints) {
        var prev = data.length > 0 ? data[data.length - 1] : 50,
          y = prev + Math.random() * 10 - 5;
        if (y < 0) {
          y = 0;
        } else if (y > 100) {
          y = 100;
        }
        data.push(Math.round(y*100)/100);
      }
      // Zip the generated y values with the x values
      var res = [];
      for (var i = 0; i < data.length; ++i) {
        res.push([i, data[i]])
      }
      return res;
    }

    $scope.d4 = $scope.getRandomData();
  }]);