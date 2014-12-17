function transactionItemController($rootScope, $scope, $log) {
	$rootScope.viewName = 'Transactions';

	$log.debug('transactionItemController...');
}
appControllers.controller('transactionItemController',
		transactionItemController);
