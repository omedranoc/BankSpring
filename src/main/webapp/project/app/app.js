var bankApp = angular.module('BankApp', [ 'ui.router' ]);

bankApp.config(function($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.otherwise('/home');

	$stateProvider
	
	.state('login', {
		url : '/login',
		templateUrl : 'project/app/templates/login.html',
		controller : 'LoginCtrl as login'
	}).state('register', {
		url : '/register',
		templateUrl : 'project/app/templates/register.html',
		controller : 'RegisterCtrl as register'
	}).state('account', {
		url : '/account',
		templateUrl : 'project/app/templates/account.html',
		controller : 'AccountCtrl as account'
	}).state('account.deposit', {
		url : '/account/deposit',
		templateUrl : 'project/app/templates/deposit.html'
	}).state('account.withdraw', {
		url : '/account/withdraw',
		templateUrl : 'project/app/templates/withdraw.html'
	});

});

bankApp.service('AccountService', function($http, $q) {

	var accountService = this;
	accountService.message = "";

	accountService.account = {
		id : 0,
		balance : '',
		amount : ''
	};

	accountService.getAccount = function() {
		return accountService.account;
	};

	accountService.setAccount = function(account) {
		accountService.account = account;
	};

	accountService.getMessage = function() {
		return accountService.message;
	};

	accountService.setMessage = function(message) {
		accountService.message = message;
	};

	accountService.fetchAccountByUserId = function(userId) {

		console.log('User ID: ' + userId);

		var promise = $http.get(
				'http://localhost:8081/BankSpring/account/findByUserId', {
					params : {
						userId : userId
					}
				}).then(function(response) {
			console.log('Account Success');
			console.log(response);
			return response;
		}, function(response) {
			console.log('Account Failure');
			return $q.reject(response);
		});

		return promise;

	};

	accountService.fetchAccountByAccountId = function(accountId) {

		var promise = $http.get(
				'http://localhost:8081/BankSpring/account/findByAccountId', {
					params : {
						accountId : accountId
					}
				}).then(function(response) {
			console.log('Account Success');
			//console.log(response);
			return response;
		}, function(response) {
			//console.log('Account Failure');
			return $q.reject(response);
		});

		return promise;

	};

	accountService.deposit = function(account, amount, user) {

		var promise = $http.post(
				'http://localhost:8081/BankSpring/account/deposit', {
					id : account.id,
					amount : amount,
					balance : account.balance,
					userId : user.id
				}).then(function(response) {
			//console.log('Deposit Success');
			//console.log(response);
			return response;
		}, function(response) {

			//console.log('Deposit Failure');
			//console.log(response);
			return $q.reject(response);
		});

		return promise;
	};

	accountService.withdraw = function(account, amount, user) {

		var promise = $http.post(
				'http://localhost:8081/BankSpring/account/withdraw', {
					id : account.id,
					amount : amount,
					balance : account.balance,
					userId : user.id
				}).then(function(response) {
			//console.log('Withdraw Success');
			//console.log(response);
			return response;
		}, function(response) {
			//console.log('Withdraw Failure');
			//console.log(response);
			return $q.reject(response);
		});

		return promise;
	};
});

bankApp.controller('AccountCtrl',
		function(AccountService, UserService, $state) {

			var account = this;

			//console.log(UserService.getUser());

			account.curAccount = AccountService.getAccount();
			account.amount = 0;
			account.curUser = UserService.getUser();

			var promise = AccountService.fetchAccountByUserId(UserService
					.getUser().id);

			promise.then(function(response) {
				AccountService.setAccount(response.data);
				account.curAccount = AccountService.getAccount();
			}, function(error) {
				//console.log('failure');

			});

			account.deposit = function(isValid) {

				//console.log('Depositing');

				var depositPromise = AccountService.deposit(account.curAccount,
						account.amount, account.curUser);

				depositPromise.then(function(response) {

					AccountService.setAccount(response.data);
					account.curAccount = AccountService.getAccount();

				}, function(error) {

					//console.log('failure');

				});

			};

			account.withdraw = function(isValid) {

				//console.log('Withdrawing');

				var depositPromise = AccountService.withdraw(
						account.curAccount, account.amount, account.curUser);

				depositPromise.then(function(response) {

					AccountService.setAccount(response.data);
					account.curAccount = AccountService.getAccount();

				}, function(error) {

					//console.log('failure');

				});

			};

		});

bankApp.service('UserService', function($http, $q) {

	var userService = this;
	userService.message = "";

	userService.user = {
		id : 0,
		username : "",
		password : "",
		authenticated : false
	};

	userService.getMessage = function() {
		return userService.message;
	};

	userService.setMessage = function(message) {
		userService.message = message;
	};

	userService.getUser = function() {
		return userService.user;
	};

	userService.setUser = function(user) {
		userService.user = user;
	};

	userService.authenticateUser = function(user) {

		var promise = $http.post(
				'http://localhost:8081/BankSpring/user/authenticate', user)
			.then(function(response) {
					//console.log('Log Success');
					return response;
				}, function(response) {
					//console.log('Log Failure');
					return $q.reject(response);
				});
		return promise;
	};

	userService.registerUser = function(user) {

		var promise = $http.post(
				'http://localhost:8081/BankSpring/user/register', user).then(
						function(response) {
							console.log('Regi Success');
							return response;
						}, function(response) {
							console.log('Regi Failure');
							console.log(response);
							return $q.reject(response);
						});
		return promise;
	};

});

bankApp.controller('LoginCtrl', function(UserService, $state) {

	var login = this;

	login.user = UserService.getUser();

	login.message = UserService.getMessage();

	login.doLogin = function(isValid) {

		if (isValid) {

			var promise = UserService.authenticateUser(login.user);

			promise.then(function(response) {
				//console.log('success');
				//console.log(response);
				UserService.setUser(response.data);
				$state.go('account');
			}, function(error) {
				//console.log('failure');
				login.message = 'Bad credentials';
				$state.go('login')
			});

		}

	};

});

bankApp.controller('RegisterCtrl', function(UserService, $state) {

	var register = this;

	register.user = {
		username : '',
		password : ''
	};

	register.message = UserService.getMessage();

	register.doRegister = function(isValid) {

		if (isValid) {

			var promise = UserService.registerUser(register.user);

			promise.then(function(data) {
				console.log('success');
				console.log(data);
				UserService.setMessage('Successful registration!');
				$state.go('login');
			}, function(error) {
				console.log('failure');
				console.log(error);
				register.message = 'Username is taken';
				$state.go('register')
			});

		}

	};

});

bankApp.controller('NavBarCtrl', function(UserService, $state) {

	var nav = this;

	nav.curUser = UserService.getUser();

	nav.isActive = function(viewLocation) {
		nav.curUser = UserService.getUser();
		nav.curState = $state.current.url;
		return viewLocation === $state.current.url;
	};

	nav.logout = function() {
		UserService.setUser(null);
	};

});
