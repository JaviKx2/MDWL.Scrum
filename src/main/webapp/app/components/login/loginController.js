booking.controller('LoginController', function($http, $window, loginService) {
	"use strict";
	
	var vm = this;
	
	vm.loginData = {};
	vm.error = false;
	vm.showResult = false;
	
	vm.login = () => {
		loginService.login(vm.loginData).then(result => {
			if (result.token){
				console.log("token: " + result.token);
				$window.localStorage['spring-token'] = result.token;
		        $http.defaults.headers.common['x-access-token'] = result.token;
				vm.error = false;
			} else {
				console.log("ERROR");
				vm.error = true;
			}
		}, errors => {	
			vm.error = true;
		});
		vm.loginData = {};
		vm.showResult = true;
	}
	
		
});