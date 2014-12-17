function loginController($rootScope, $scope, $log, $location, $sessionStorage,
		bootstrapNotifyService, sessionService, $timeout) {
	$rootScope.viewName = 'SignIn';

	var wydNotifyService = bootstrapNotifyService;

	$scope.message = null

	$scope.user = {
		id : 'vteial',
		password : ''
	};

	function signin() {
		wydNotifyService.removeAll();
		$log.info('singing in...');
		$scope.message = null;

		sessionService.login($scope.user).then(function(response) {
			$log.info(response);
			$scope.user.password = '';
			$rootScope.sessionUser = $scope.user;
			$rootScope.isLoggedIn = true;
			$rootScope.homeView = '/home';
			var locPath = $sessionStorage.currentLocationPath;
			$log.info('Last Stored Location : ', locPath);
			if (!locPath) {
				locPath = $rootScope.homeView;
			}
			$location.path(locPath);
		})

	}
	$scope.signin = signin;

	// $timeout(function() {
	// $log.info('Before signin...');
	// $scope.signin();
	// $log.info('After signin...');
	// }, 1000);

	$log.debug('loginController...');
}
appControllers.controller('loginController', loginController);
