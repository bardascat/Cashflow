app.directive('datetimez', function() {
    return {
        restrict: 'A',
        require : 'ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
          element.datetimepicker({
            dateFormat:'yyyy-mm-dd',
           language: 'en',
           pickTime: false,
           startDate: '01-11-2013',      // set a minimum date
           endDate: '01-11-2030'          // set a maximum date
          }).on('changeDate', function(e) {
            ngModelCtrl.$setViewValue(e.date);
            scope.$apply();
          });
        }
    };
});