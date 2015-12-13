'use strict';

/**
 * Config for the router
 */
angular.module('app')
  .run(['$rootScope','$state', '$stateParams','$localStorage','loginService','$location','$http',
        function ($rootScope,   $state,   $stateParams,$localStorage,loginService,$location,$http) {


        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;

        $rootScope.currentUser = $localStorage.currentUser;

        console.log("CurrentUser:");
        console.log($rootScope.currentUser);
        $http.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        if ($rootScope.currentUser) {
            $http.defaults.headers.common['X-AUTH-TOKEN'] = $rootScope.currentUser.token;
        }
    
      	
      	$rootScope.$on("$locationChangeStart",function(event,next,current){
      	
      		console.log("$locationChangeStart - "+next);
      		
      		//user is not logged in. Redirect to /login page
      		if(!$rootScope.currentUser && $location.path()!="/login"){
      			console.log("Redirect user to login page.");
      			$location.path("/access/login");
      		}
      		//user is already logged in. Redirect him from /login page
      		else if($rootScope.currentUser &&($location.path()=="/login")){
      			console.log("User is already logged in. Redirect to / page")
      			$location.path("/");
      		}

      	})
      }
    ]
  )
  .config(
    [          '$stateProvider', '$urlRouterProvider', 'JQ_CONFIG', 
      function ($stateProvider,   $urlRouterProvider, JQ_CONFIG) {
          
          $urlRouterProvider
              .otherwise('/app/dashboard-v1');
          $stateProvider
              .state('app', {
                  abstract: true,
                  url: '/app',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/app.html'
              })
              
              
              .state('app.dashboard-v1', {
                  url: '/dashboard-v1',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/app_dashboard_v1.html',
                  resolve: {
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                        return $ocLazyLoad.load([JQ_CONFIG.SOURCE_PATH+'/js/controllers/dashboardController.js',JQ_CONFIG.SOURCE_PATH + '/js/services/reportService.js']);
                    }]
                  }
              })
              //budget
              .state('app.budget', {
                  url: '/budget/add',
                  templateUrl: JQ_CONFIG.SOURCE_PATH + '/tpl/budget/budget.html',
                  resolve: {
                      deps: ['uiLoad',
                          function (uiLoad) {
                              return uiLoad.load([JQ_CONFIG.SOURCE_PATH + '/js/controllers/budgetController.js',
                                      JQ_CONFIG.SOURCE_PATH + '/js/services/budgetService.js'
                              ]);
                          }]
                  }
              })
              //transaction
              .state('app.transaction', {
                  template: '<div ui-view class="fade-in"></div>',
                  resolve: {
                      deps: ['uiLoad',
                          function (uiLoad) {
                              return uiLoad.load([JQ_CONFIG.SOURCE_PATH + '/js/services/transactionService.js',
                                      JQ_CONFIG.SOURCE_PATH + '/js/controllers/transactionController.js',
                                      JQ_CONFIG.SOURCE_PATH + '/js/services/categoryService.js',
                              ]);
                          }]
                  }
              })
              .state('app.transaction.add', {
                  url: "/transaction/add/:type",
                  templateUrl: JQ_CONFIG.SOURCE_PATH + '/tpl/transaction/transaction.html'
                  //templateUrl:JQ_CONFIG.SOURCE_PATH+'/tpl/category/category.html'
              })
              .state('app.transaction.list', {
                  url: "/transaction/list",
                  templateUrl: JQ_CONFIG.SOURCE_PATH + '/tpl/transaction/transaction-list.html'
              })
              .state('app.transaction.edit', {
                  url: "/transaction/:id",
                  templateUrl: JQ_CONFIG.SOURCE_PATH + '/tpl/transaction/transaction.html'
              })
              .state('app.dashboard-v2', {
                  url: '/dashboard-v2',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/app_dashboard_v2.html',
                  resolve: {
                    deps: ['$ocLazyLoad',
                      function( $ocLazyLoad ){
                        return $ocLazyLoad.load([JQ_CONFIG.SOURCE_PATH+'/js/controllers/chart.js']);
                    }]
                  }
              })
              .state('app.ui', {
                  url: '/ui',
                  template: '<div ui-view class="fade-in-up"></div>'
              })
              .state('app.ui.buttons', {
                  url: '/buttons',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/ui_buttons.html'
              })
              .state('app.ui.icons', {
                  url: '/icons',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/ui_icons.html'
              })
              .state('app.ui.grid', {
                  url: '/grid',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/ui_grid.html'
              })
              .state('app.ui.widgets', {
                  url: '/widgets',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/ui_widgets.html'
              })          
              .state('app.ui.bootstrap', {
                  url: '/bootstrap',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/ui_bootstrap.html'
              })
              .state('app.ui.sortable', {
                  url: '/sortable',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/ui_sortable.html'
              })
              .state('app.ui.scroll', {
                  url: '/scroll',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/ui_scroll.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad){
                          return uiLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/scroll.js');
                      }]
                  }
              })
              .state('app.ui.portlet', {
                  url: '/portlet',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/ui_portlet.html'
              })
              .state('app.ui.timeline', {
                  url: '/timeline',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/ui_timeline.html'
              })
              .state('app.ui.tree', {
                  url: '/tree',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/ui_tree.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('angularBootstrapNavTree').then(
                              function(){
                                 return $ocLazyLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/tree.js');
                              }
                          );
                        }
                      ]
                  }
              })
              .state('app.ui.toaster', {
                  url: '/toaster',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/ui_toaster.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad){
                          return $ocLazyLoad.load('toaster').then(
                              function(){
                                 return $ocLazyLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/toaster.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.ui.jvectormap', {
                  url: '/jvectormap',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/ui_jvectormap.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad){
                          return $ocLazyLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/vectormap.js');
                      }]
                  }
              })
              .state('app.ui.googlemap', {
                  url: '/googlemap',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/ui_googlemap.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( [
                            'js/app/map/load-google-maps.js',
                            'js/app/map/ui-map.js',
                            'js/app/map/map.js'] ).then(
                              function(){
                                return loadGoogleMaps(); 
                              }
                            );
                      }]
                  }
              })
              .state('app.chart', {
                  url: '/chart',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/ui_chart.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad){
                          return uiLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/chart.js');
                      }]
                  }
              })
              // table
              .state('app.table', {
                  url: '/table',
                  template: '<div ui-view></div>'
              })
              .state('app.table.static', {
                  url: '/static',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/table_static.html'
              })
              .state('app.table.datatable', {
                  url: '/datatable',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/table_datatable.html'
              })
              .state('app.table.footable', {
                  url: '/footable',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/table_footable.html'
              })
              .state('app.table.grid', {
                  url: '/grid',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/table_grid.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ngGrid').then(
                              function(){
                                  return $ocLazyLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/grid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.uigrid', {
                  url: '/uigrid',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/table_uigrid.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ui.grid').then(
                              function(){
                                  return $ocLazyLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/uigrid.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.editable', {
                  url: '/editable',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/table_editable.html',
                  controller: 'XeditableCtrl',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('xeditable').then(
                              function(){
                                  return $ocLazyLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/xeditable.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.table.smart', {
                  url: '/smart',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/table_smart.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('smart-table').then(
                              function(){
                                  return $ocLazyLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/table.js');
                              }
                          );
                      }]
                  }
              })

              // form
              .state('app.form', {
                  url: '/form',
                  template: '<div ui-view class="fade-in"></div>',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/form.js');
                      }]
                  }
              })
             
              .state('app.form.components', {
                  url: '/components',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/form_components.html',
                  resolve: {
                      deps: ['uiLoad', '$ocLazyLoad',
                        function( uiLoad, $ocLazyLoad ){
                          return uiLoad.load( JQ_CONFIG.daterangepicker )
                          .then(
                              function(){
                                return uiLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/form.components.js');
                              }
                          ).then(
                              function(){
                                return $ocLazyLoad.load('ngBootstrap');
                              }
                          );
                        }
                      ]
                  }
              })
              .state('app.form.elements', {
                  url: '/elements',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/form_elements.html'
              })
              .state('app.form.validation', {
                  url: '/validation',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/form_validation.html'
              })
              .state('app.form.wizard', {
                  url: '/wizard',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/form_wizard.html'
              })
              .state('app.form.fileupload', {
                  url: '/fileupload',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/form_fileupload.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad){
                          return $ocLazyLoad.load('angularFileUpload').then(
                              function(){
                                 return $ocLazyLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/file-upload.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.form.imagecrop', {
                  url: '/imagecrop',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/form_imagecrop.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad){
                          return $ocLazyLoad.load('ngImgCrop').then(
                              function(){
                                 return $ocLazyLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/imgcrop.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.form.select', {
                  url: '/select',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/form_select.html',
                  controller: 'SelectCtrl',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('ui.select').then(
                              function(){
                                  return $ocLazyLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/select.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.form.slider', {
                  url: '/slider',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/form_slider.html',
                  controller: 'SliderCtrl',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('vr.directives.slider').then(
                              function(){
                                  return $ocLazyLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/slider.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.form.editor', {
                  url: '/editor',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/form_editor.html',
                  controller: 'EditorCtrl',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('textAngular').then(
                              function(){
                                  return $ocLazyLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/editor.js');
                              }
                          );
                      }]
                  }
              })
              .state('app.form.xeditable', {
                  url: '/xeditable',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/form_xeditable.html',
                  controller: 'XeditableCtrl',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load('xeditable').then(
                              function(){
                                  return $ocLazyLoad.load(JQ_CONFIG.SOURCE_PATH+'/js/controllers/xeditable.js');
                              }
                          );
                      }]
                  }
              })
              // pages
              .state('app.page', {
                  url: '/page',
                  template: '<div ui-view class="fade-in-down"></div>'
              })
              .state('app.page.profile', {
                  url: '/profile',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/page_profile.html'
              })
              .state('app.page.post', {
                  url: '/post',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/page_post.html'
              })
              .state('app.page.search', {
                  url: '/search',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/page_search.html'
              })
              .state('app.page.invoice', {
                  url: '/invoice',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/page_invoice.html'
              })
              .state('app.page.price', {
                  url: '/price',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/page_price.html'
              })
              .state('app.docs', {
                  url: '/docs',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/docs.html'
              })
              // others
              .state('lockme', {
                  url: '/lockme',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/page_lockme.html'
              })
              .state('access', {
                  url: '/access',
                  template: '<div ui-view class="fade-in-right-big smooth"></div>'
              })
              .state('access.signin', {
                  url: '/login',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/page_signin.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( [JQ_CONFIG.SOURCE_PATH+'/js/controllers/loginController.js'] );
                      }]
                  }
              })
              .state('access.signup', {
                  url: '/register',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/page_signup.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( [JQ_CONFIG.SOURCE_PATH+'/js/controllers/signup.js'] );
                      }]
                  }
              })
              .state('access.forgotpwd', {
                  url: '/forgotpwd',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/page_forgotpwd.html'
              })
              .state('access.404', {
                  url: '/404',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/page_404.html'
              })

              // fullCalendar
              .state('app.calendar', {
                  url: '/calendar',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/app_calendar.html',
                  // use resolve to load other dependences
                  resolve: {
                      deps: ['$ocLazyLoad', 'uiLoad',
                        function( $ocLazyLoad, uiLoad ){
                          return uiLoad.load(
                            JQ_CONFIG.fullcalendar.concat('js/app/calendar/calendar.js')
                          ).then(
                            function(){
                              return $ocLazyLoad.load('ui.calendar');
                            }
                          )
                      }]
                  }
              })

              // mail
              .state('app.mail', {
                  abstract: true,
                  url: '/mail',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/mail.html',
                  // use resolve to load other dependences
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/app/mail/mail.js',
                                               'js/app/mail/mail-service.js',
                                               JQ_CONFIG.moment] );
                      }]
                  }
              })
              .state('app.mail.list', {
                  url: '/inbox/{fold}',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/mail.list.html'
              })
              .state('app.mail.detail', {
                  url: '/{mailId:[0-9]{1,4}}',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/mail.detail.html'
              })
              .state('app.mail.compose', {
                  url: '/compose',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/mail.new.html'
              })

              .state('layout', {
                  abstract: true,
                  url: '/layout',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/layout.html'
              })
              .state('layout.fullwidth', {
                  url: '/fullwidth',
                  views: {
                      '': {
                          templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/layout_fullwidth.html'
                      },
                      'footer': {
                          templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/layout_footer_fullwidth.html'
                      }
                  },
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( [JQ_CONFIG.SOURCE_PATH+'/js/controllers/vectormap.js'] );
                      }]
                  }
              })
              .state('layout.mobile', {
                  url: '/mobile',
                  views: {
                      '': {
                          templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/layout_mobile.html'
                      },
                      'footer': {
                          templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/layout_footer_mobile.html'
                      }
                  }
              })
              .state('layout.app', {
                  url: '/app',
                  views: {
                      '': {
                          templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/layout_app.html'
                      },
                      'footer': {
                          templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/layout_footer_fullwidth.html'
                      }
                  },
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( [JQ_CONFIG.SOURCE_PATH+'/js/controllers/tab.js'] );
                      }]
                  }
              })
              .state('apps', {
                  abstract: true,
                  url: '/apps',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/layout.html'
              })
              .state('apps.note', {
                  url: '/note',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/apps_note.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/app/note/note.js',
                                               JQ_CONFIG.moment] );
                      }]
                  }
              })
              .state('apps.contact', {
                  url: '/contact',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/apps_contact.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/app/contact/contact.js'] );
                      }]
                  }
              })
              .state('app.weather', {
                  url: '/weather',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/apps_weather.html',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load(
                              {
                                  name: 'angular-skycons',
                                  files: ['js/app/weather/skycons.js',
                                          'js/app/weather/angular-skycons.js',
                                          'js/app/weather/ctrl.js',
                                          JQ_CONFIG.moment ] 
                              }
                          );
                      }]
                  }
              })
              .state('app.todo', {
                  url: '/todo',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/apps_todo.html',
                  resolve: {
                      deps: ['uiLoad',
                        function( uiLoad ){
                          return uiLoad.load( ['js/app/todo/todo.js',
                                               JQ_CONFIG.moment] );
                      }]
                  }
              })
              .state('app.todo.list', {
                  url: '/{fold}'
              })
              .state('music', {
                  url: '/music',
                  templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/music.html',
                  controller: 'MusicCtrl',
                  resolve: {
                      deps: ['$ocLazyLoad',
                        function( $ocLazyLoad ){
                          return $ocLazyLoad.load([
                            'com.2fdevs.videogular', 
                            'com.2fdevs.videogular.plugins.controls', 
                            'com.2fdevs.videogular.plugins.overlayplay',
                            'com.2fdevs.videogular.plugins.poster',
                            'com.2fdevs.videogular.plugins.buffering',
                            'js/app/music/ctrl.js', 
                            'js/app/music/theme.css'
                          ]);
                      }]
                  }
              })
                .state('music.home', {
                    url: '/home',
                    templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/music.home.html'
                })
                .state('music.genres', {
                    url: '/genres',
                    templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/music.genres.html'
                })
                .state('music.detail', {
                    url: '/detail',
                    templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/music.detail.html'
                })
                .state('music.mtv', {
                    url: '/mtv',
                    templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/music.mtv.html'
                })
                .state('music.mtvdetail', {
                    url: '/mtvdetail',
                    templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/music.mtv.detail.html'
                })
                .state('music.playlist', {
                    url: '/playlist/{fold}',
                    templateUrl: JQ_CONFIG.SOURCE_PATH+'/tpl/music.playlist.html'
                })
      }
    ]
  );
