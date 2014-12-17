function itemController($rootScope, $scope, $log) {
	$rootScope.viewName = 'Items';

	$log.debug('itemController...');
}
appControllers.controller('itemController', itemController);
