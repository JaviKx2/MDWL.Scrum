booking.controller('LoginController', function($location, loginService, BookingFactory) {
	"use strict";
	
	var vm = this;
	
	vm.loginData = {};
	vm.error = false;
	vm.showResult = false;
	
	vm.login = () => {
		loginService.login(vm.loginData).then(result => {
			if (result.token){
				vm.error = false;
				BookingFactory.saveToken(result.token);
				var redirectAfterLogin = BookingFactory.getRedirectAfterLogin();
				if (redirectAfterLogin !== '') {
					BookingFactory.clearRedirectAfterLogin()
					$location.path(redirectAfterLogin);
				} else {
					$location.path("/search");
				}
			} else {
				vm.error = true;
			}
		}, errors => {	
			vm.error = true;
		});
		vm.loginData = {};
		vm.showResult = true;
	}
	
		
});