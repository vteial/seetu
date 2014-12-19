<html lang="en" class="no-js" x-ng-app="app" x-ng-csp x-ng-controller="rootController">
<head>
<title>Seetu</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css" >
<link rel="stylesheet" href="assets/fontawesome/css/font-awesome.min.css">
<!-- 
<link rel="stylesheet" href="assets/ui-select/selectize.bootstrap3.css" />
<link rel="stylesheet" href="assets/ui-select/select.min.css" />
-->
<link rel="stylesheet" href="assets/style-1.css" />
<link rel="stylesheet" href="assets/style-0.css" />

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

</head>
<body x-ng-clock>

<br/><br/><br/>

<section>
	<div class="text-center">
		<h1>Welcome to Seetu!</h1>
	</div>
	<div class="text-center">
		&nbsp;
	</div>
	<div class="text-center">
		<a href="index-d.html" class="btn btn-primary">Destop/Tablet</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="index-m.html" class="btn btn-primary">Tablet/Mobile</a>
	</div>
</section>

<br/><br/><br/>
<%
	if(true) {
%>
<footer>
	<div class="text-center">
		<small>
   			<a class="navbar-link"  href="_ah/admin/datastore/">Google Datastore</a>
   			<b>|</b>
   			<a class="navbar-link"  href="_ah/gaelyk-datastore-viewer/browse">Google Datastore</a>
   			<b>|</b>
   			<a class="navbar-link"  href="_ah/gaelyk-console/">Gaelyk Console</a>
   			<b>|</b>
   			<a  class="navbar-link" href="init/reset">Reset b</a>
		</small>
	</div>
</footer>

<%	
}
%>

<script type="text/javascript"
	src="assets/lib/lodash.min.js"></script>
<script type="text/javascript"
	src="assets/lib/underscore.string.min.js"></script>
<script type="text/javascript" 
	src="assets/lib/moment.min.js"></script>
<script type="text/javascript" 
	src="assets/lib/jquery.min.js"></script>
<script type="text/javascript" 
	src="assets/lib/holder.js"></script>
	
<script type="text/javascript"
	src="assets/angular/13x/angular.js"></script>
<script type="text/javascript"
	src="assets/angular/13x/angular-route.min.js"></script>
<script type="text/javascript"
	src="assets/angular/13x/angular-sanitize.min.js"></script>

<script type="text/javascript" 
	src="assets/lib/ngStorage.min.js"></script>

<script type="text/javascript" src="modules/init.js"></script>
<script type="text/javascript" src="modules/home/indexController.js"></script>

<script type="text/javascript" src="modules/common.js"></script>
<script type="text/javascript">

function rootController($scope, $log) {
	$log.info('root...');
}
appControllers.controller('rootController', rootController);

var dependents = [ 'ngRoute', 'ngSanitize' ];
dependents.push('ngStorage');
dependents.push('app.filters');
dependents.push('app.directives');
dependents.push('app.services');
dependents.push('app.controllers');
var app = angular.module('app', dependents);

app.config(function($httpProvider) {
	$httpProvider.interceptors.push('generalHttpInterceptor');
});

function appInit($log, $window, $sessionStorage) {
	$log.info('Initialization started...');

	_.mixin(_.str.exports());

	
	$log.info('Initialization finished...');
}
app.run([ '$log', '$window', '$sessionStorage', appInit ]);

</script>

</body>
</html>