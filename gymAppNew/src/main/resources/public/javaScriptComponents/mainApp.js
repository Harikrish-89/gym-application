/**
 * 
 */
var gymApp = angular.module('gymApp',[
  'ngRoute',
  'ngMaterial',
  'gymAppControllers',
  'gymAppServices',
  'ngTable',
  'ngCsv',
  'ngAnimate',
  'ngIdle'
 ]);

gymApp.config(['$routeProvider','$httpProvider','IdleProvider','KeepaliveProvider',
		function($routeProvider,$httpProvider,IdleProvider,KeepaliveProvider){
	  IdleProvider.idle(5*60); // 10 minutes idle
	  IdleProvider.timeout(5*60); // after 30 seconds idle, time the user out
	  KeepaliveProvider.interval(5*60); // 5 minute keep-alive ping
	$routeProvider.
	when('/adminLogin',{
		templateUrl: 'partials/AdminLogin.html',
		controller: 'adminLoginController'
	}).
	when('/getMemberDetails',{
		templateUrl:'partials/MemberDetails.html',
		controller: 'memberDetailsController'
	}).
	when('/addNewMember',{
		templateUrl: 'partials/AddNewMember.html',
		controller: 'addNewMemberController'
	}).
	when('/generateReports',{
		templateUrl: 'partials/Reports.html',
		controller: 'generateReportsController'
	}).
	otherwise({
		redirectTo: '/adminLogin'
	});
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}]);

gymApp.run(['$rootScope','Idle','$http','$location',function($rootScope,Idle,$http,$location){
	Idle.watch();
	$rootScope.authenticated=false;
	$rootScope.showHome=false;
	$rootScope.showLogout=false;
	$rootScope.$on('IdleTimeout', function() {
		 $rootScope.showHome=false;
		  $http.post('logout', {}).finally(function() {
		    $rootScope.authenticated = false;
		    $rootScope.showLogout=false;
		    $location.path("/");
	    });});
	/*$rootScope.$on('$routeChangeStart', function(e, curr, prev) { 
	    if (curr.$$route && curr.$$route.resolve) {
	      // Show a loading message until promises are not resolved
	    	$rootScope.loadingView = true;
	    }
	  });

		$rootScope.$on('$routeChangeSuccess', function(e, curr, prev) { 
	    // Hide loading message
			$rootScope.loadingView = false;
	  });*/
	}]);


var dailyLoginApp=angular.module('dailyLoginApp',['ngRoute',
                                                  'gymAppControllers',
                                                  'gymAppServices',
                                                  'ngMaterial'
                                                  ]);
dailyLoginApp.config(['$routeProvider','$httpProvider',
       		function($routeProvider,$httpProvider){
       	$routeProvider.
       	when('/dailyLogin',{
       		templateUrl: 'partials/DailyLogin.html',
       		controller: 'dailyLoginController'
       	}).
       	otherwise({
       		redirectTo: '/dailyLogin'
       	});
       	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
       }]);