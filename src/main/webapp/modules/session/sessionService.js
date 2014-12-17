function sessionService($log, $http, $q) {
	var basePath = 'sessions';

	var service = {
		context : {}
	};

	service.login = function(user) {
		var path = basePath + '/login';

		var deferred = $q.defer();
		$http.post(path, user).success(function(response) {
			deferred.resolve(response);
		}).error(function() {
			deferred.reject("unable to authenticate...");
		});

		return deferred.promise;
	};

	service.logout = function() {
		var path = basePath + '/logout';

		var deferred = $q.defer();
		$http.get(path).success(function(data) {
			deferred.resolve(data);
		}).error(function() {
			deferred.reject("unable to logout...");
		});

		return deferred.promise;
	};
	return service;
}
appServices.factory('sessionService', sessionService);