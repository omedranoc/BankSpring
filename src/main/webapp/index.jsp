<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html ng-app="BankApp">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AngularJS: UI-Router Quick Start</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body class="container">

	<nav class="navbar navbar-default" ng-controller="NavBarCtrl as nav">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Quick Start</a>
			</div>
			<div id="navbar">
				<ul class="nav navbar-nav">
					<li ng-class="{ active: nav.isActive('/')}"><a ui-sref="home">Home</a></li>
					<li ng-hide="nav.curUser.authenticated" ng-class="{ active: nav.isActive('/login')}"><a ui-sref="login">Login</a></li>
					<li ng-hide="nav.curUser.authenticated" ng-class="{ active: nav.isActive('/register')}"><a ui-sref="register">Register</a></li>
					<li ng-show="nav.curUser.authenticated" ng-class="{ active: nav.isActive('/account')}"><a ui-sref="account">Account</a></li>
					<li ng-show="nav.curUser.authenticated" ng-class="{ active: nav.isActive('/account/deposit')}"><a ui-sref="account.deposit">Deposit</a></li>
					
					<li ng-show="nav.curUser.authenticated" ng-click="nav.logout()"><a ui-sref="login">Logout</a>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="span12">
				<div class="well" ui-view></div>
			</div>
		</div>
	</div>
	<!-- Angular -->
	<script
		src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.2/angular.js"></script>
	<!-- UI-Router -->
	<script
		src="//angular-ui.github.io/ui-router/release/angular-ui-router.js"></script>

	<!-- App Script -->
	<script src="${ pageContext.request.contextPath }/project/app/app.js"
		type="text/javascript"></script>


</body>

</html>