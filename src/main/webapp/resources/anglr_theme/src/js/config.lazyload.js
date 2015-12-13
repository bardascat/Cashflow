// lazyload config

angular.module('app')
    /**
   * jQuery plugin config use ui-jq directive , config the js and css files that required
   * key: function name of the jQuery plugin
   * value: array of the css js file located
   */
  .constant('JQ_CONFIG', {
	  API_URL:"http://localhost:8833",
	  SOURCE_PATH:'/resources/anglr_theme/src',
      easyPieChart:   [   '/resources/anglr_theme/bower_components/jquery.easy-pie-chart/dist/jquery.easypiechart.fill.js'],
      sparkline:      [   '/resources/anglr_theme/bower_components/jquery.sparkline/dist/jquery.sparkline.retina.js'],
      plot:           [   '/resources/anglr_theme/bower_components/flot/jquery.flot.js',
                          '/resources/anglr_theme/bower_components/flot/jquery.flot.pie.js', 
                          '/resources/anglr_theme/bower_components/flot/jquery.flot.resize.js',
                          '/resources/anglr_theme/bower_components/flot.tooltip/js/jquery.flot.tooltip.js',
                          '/resources/anglr_theme/bower_components/flot.orderbars/js/jquery.flot.orderBars.js',
                          '/resources/anglr_theme/bower_components/flot-spline/js/jquery.flot.spline.js'],
      moment:         [   '/resources/anglr_theme/bower_components/moment/moment.js'],
      screenfull:     [   '/resources/anglr_theme/bower_components/screenfull/dist/screenfull.min.js'],
      slimScroll:     [   '/resources/anglr_theme/bower_components/slimscroll/jquery.slimscroll.min.js'],
      sortable:       [   '/resources/anglr_theme/bower_components/html5sortable/jquery.sortable.js'],
      nestable:       [   '/resources/anglr_theme/bower_components/nestable/jquery.nestable.js',
                          '/resources/anglr_theme/bower_components/nestable/jquery.nestable.css'],
      filestyle:      [   '/resources/anglr_theme/bower_components/bootstrap-filestyle/src/bootstrap-filestyle.js'],
      slider:         [   '/resources/anglr_theme/bower_components/bootstrap-slider/bootstrap-slider.js',
                          '/resources/anglr_theme/bower_components/bootstrap-slider/bootstrap-slider.css'],
      chosen:         [   '/resources/anglr_theme/bower_components/chosen/chosen.jquery.min.js',
                          '/resources/anglr_theme/bower_components/bootstrap-chosen/bootstrap-chosen.css'],
      TouchSpin:      [   '/resources/anglr_theme/bower_components/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js',
                          '/resources/anglr_theme/bower_components/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.css'],
      wysiwyg:        [   '/resources/anglr_theme/bower_components/bootstrap-wysiwyg/bootstrap-wysiwyg.js',
                          '/resources/anglr_theme/bower_components/bootstrap-wysiwyg/external/jquery.hotkeys.js'],
      dataTable:      [   '/resources/anglr_theme/bower_components/datatables/media/js/jquery.dataTables.min.js',
                          '/resources/anglr_theme/bower_components/plugins/integration/bootstrap/3/dataTables.bootstrap.js',
                          '/resources/anglr_theme/bower_components/plugins/integration/bootstrap/3/dataTables.bootstrap.css'],
      vectorMap:      [   '/resources/anglr_theme/bower_components/bower-jvectormap/jquery-jvectormap-1.2.2.min.js', 
                          '/resources/anglr_theme/bower_components/bower-jvectormap/jquery-jvectormap-world-mill-en.js',
                          '/resources/anglr_theme/bower_components/bower-jvectormap/jquery-jvectormap-us-aea-en.js',
                          '/resources/anglr_theme/bower_components/bower-jvectormap/jquery-jvectormap-1.2.2.css'],
      footable:       [   '/resources/anglr_theme/bower_components/footable/dist/footable.all.min.js',
                          '/resources/anglr_theme/bower_components/footable/css/footable.core.css'],
      fullcalendar:   [   '/resources/anglr_theme/bower_components/moment/moment.js',
                          '/resources/anglr_theme/bower_components/fullcalendar/dist/fullcalendar.min.js',
                          '/resources/anglr_theme/bower_components/fullcalendar/dist/fullcalendar.css',
                          '/resources/anglr_theme/bower_components/fullcalendar/dist/fullcalendar.theme.css'],
      daterangepicker:[   '/resources/anglr_theme/bower_components/moment/moment.js',
                          '/resources/anglr_theme/bower_components/bootstrap-daterangepicker/daterangepicker.js',
                          '/resources/anglr_theme/bower_components/bootstrap-daterangepicker/daterangepicker-bs3.css'],
      tagsinput:      [   '/resources/anglr_theme/bower_components/bootstrap-tagsinput/dist/bootstrap-tagsinput.js',
                          '/resources/anglr_theme/bower_components/bootstrap-tagsinput/dist/bootstrap-tagsinput.css']
                      
    }
  )
  // oclazyload config
  .config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
      // We configure ocLazyLoad to use the lib script.js as the async loader
      $ocLazyLoadProvider.config({
          debug:  true,
          events: true,
          modules: [
              {
                  name: 'ngGrid',
                  files: [
                      '/resources/anglr_theme/bower_components/ng-grid/build/ng-grid.min.js',
                      '/resources/anglr_theme/bower_components/ng-grid/ng-grid.min.css',
                      '/resources/anglr_theme/bower_components/ng-grid/ng-grid.bootstrap.css'
                  ]
              },
              {
                  name: 'ui.grid',
                  files: [
                      '/resources/anglr_theme/bower_components/angular-ui-grid/ui-grid.min.js',
                      '/resources/anglr_theme/bower_components/angular-ui-grid/ui-grid.min.css',
                      '/resources/anglr_theme/bower_components/angular-ui-grid/ui-grid.bootstrap.css'
                  ]
              },
              {
                  name: 'ui.select',
                  files: [
                      '/resources/anglr_theme/bower_components/angular-ui-select/dist/select.min.js',
                      '/resources/anglr_theme/bower_components/angular-ui-select/dist/select.min.css'
                  ]
              },
              {
                  name:'angularFileUpload',
                  files: [
                    '/resources/anglr_theme/bower_components/angular-file-upload/angular-file-upload.min.js'
                  ]
              },
              {
                  name:'ui.calendar',
                  files: ['/resources/anglr_theme/bower_components/angular-ui-calendar/src/calendar.js']
              },
              {
                  name: 'ngImgCrop',
                  files: [
                      '/resources/anglr_theme/bower_components/ngImgCrop/compile/minified/ng-img-crop.js',
                      '/resources/anglr_theme/bower_components/ngImgCrop/compile/minified/ng-img-crop.css'
                  ]
              },
              {
                  name: 'angularBootstrapNavTree',
                  files: [
                      '/resources/anglr_theme/bower_components/angular-bootstrap-nav-tree/dist/abn_tree_directive.js',
                      '/resources/anglr_theme/bower_components/angular-bootstrap-nav-tree/dist/abn_tree.css'
                  ]
              },
              {
                  name: 'toaster',
                  files: [
                      '/resources/anglr_theme/bower_components/angularjs-toaster/toaster.js',
                      '/resources/anglr_theme/bower_components/angularjs-toaster/toaster.css'
                  ]
              },
              {
                  name: 'textAngular',
                  files: [
                      '/resources/anglr_theme/bower_components/textAngular/dist/textAngular-sanitize.min.js',
                      '/resources/anglr_theme/bower_components/textAngular/dist/textAngular.min.js'
                  ]
              },
              {
                  name: 'vr.directives.slider',
                  files: [
                      '/resources/anglr_theme/bower_components/venturocket-angular-slider/build/angular-slider.min.js',
                      '/resources/anglr_theme/bower_components/venturocket-angular-slider/build/angular-slider.css'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular',
                  files: [
                      '/resources/anglr_theme/bower_components/videogular/videogular.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.controls',
                  files: [
                      '/resources/anglr_theme/bower_components/videogular-controls/controls.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.buffering',
                  files: [
                      '/resources/anglr_theme/bower_components/videogular-buffering/buffering.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.overlayplay',
                  files: [
                      '/resources/anglr_theme/bower_components/videogular-overlay-play/overlay-play.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.poster',
                  files: [
                      '/resources/anglr_theme/bower_components/videogular-poster/poster.min.js'
                  ]
              },
              {
                  name: 'com.2fdevs.videogular.plugins.imaads',
                  files: [
                      '/resources/anglr_theme/bower_components/videogular-ima-ads/ima-ads.min.js'
                  ]
              },
              {
                  name: 'xeditable',
                  files: [
                      '/resources/anglr_theme/bower_components/angular-xeditable/dist/js/xeditable.min.js',
                      '/resources/anglr_theme/bower_components/angular-xeditable/dist/css/xeditable.css'
                  ]
              },
              {
                  name: 'smart-table',
                  files: [
                      '/resources/anglr_theme/bower_components/angular-smart-table/dist/smart-table.min.js'
                  ]
              }
          ]
      });
  }])
;
