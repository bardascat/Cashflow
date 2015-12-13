app.directive("customprogressbar", function($compile) {
	var obj = {
		scope:false,
		restrict : "E",		
		template : '<div  class="parent-bar progress transparent progress-small  m-t-20"  aria-valuemin="0" aria-valuemax="100"  style="width: 100%">'+
										'<div '+
											' class="child-bar progress-bar progress-bar-white "'+
											' style="width: {{data[0].width}};"> <span class="percentage_label text-muted">{{data[0].percentage}}%</span></div>'+
									'</div>',
	 link:function(scope,elem,attr){
		 //we have to compute the percentage for each bar
		 scope.data=[];
		
		 
		 scope.$watch(function(){return [attr.currentvalue,attr.maxvalue]},function(newvalue,oldvalue){
			
			currentValue=parseInt(newvalue[0]);
			maxValue=parseInt(newvalue[1]);
			if(currentValue && maxValue){
				
			 //check if we have overflow
			 if(currentValue>maxValue){
				 overflow=currentValue-maxValue;
				 	
				 //compute final percentage
				 finalPercentage=Math.round((currentValue*100)/maxValue);
				 
				 //calculate percentage for each bar
				 normalbarPercentage=Math.round(100*100/finalPercentage);
				 overflowBarPercentage=100-normalbarPercentage;
				
				scope.data.push({percentage:normalbarPercentage,width:normalbarPercentage+"%"});
			
				//add overflow progressbar
				newBar=$(elem).find(".child-bar").clone();
				newBar.removeClass("progress-bar-white");
				newBar.addClass("progress-bar-danger");
				newBar.find(".percentage_label").html(overflowBarPercentage+"%");
				newBar.find(".percentage_label").css("color","#FFF");
				
				newBar.css("width",overflowBarPercentage+"%");
				newBar.attr("aria-valuenow",overflowBarPercentage);
				
				
				
				outerHTML = $("<div />").append(newBar).html();
				scope.data.push({percentage:overflowBarPercentage,width:overflowBarPercentage+"%"});
				$(elem).find(".parent-bar").append($compile(outerHTML)(scope));
			 }else{
				 normalbarPercentage=Math.floor((currentValue*100)/maxValue);
				 scope.data.push({percentage:normalbarPercentage,width:normalbarPercentage+"%"});
			 }
			}
			 
		 },true);
		 
		 
	 }
	};

	return obj;
})