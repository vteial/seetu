function accountTransactionController($rootScope, $scope, $log) {
	$rootScope.viewName = 'Account Transactions';

	$log.debug('accountTransactionController...');
}
appControllers.controller('accountTransactionController',
		accountTransactionController);
