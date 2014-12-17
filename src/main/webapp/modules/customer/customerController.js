function customerController($rootScope, $scope, $log) {
	$rootScope.viewName = 'Customers';

	$log.debug('customerController...');
}
appControllers.controller('customerController', customerController);
