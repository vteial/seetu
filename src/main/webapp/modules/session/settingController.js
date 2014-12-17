function settingsController($rootScope, $scope, $log) {
	$rootScope.viewName = 'Settings';

	$log.debug('settingsController...');
}
appControllers.controller('settingsController', settingsController);
