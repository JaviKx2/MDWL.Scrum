booking.controller('NavbarController', function(BookingFactory, $window) {
	"use strict";
	
	var vm = this;
	
	vm.isLoggedIn = BookingFactory.isLoggedIn();
	
	vm.permissions = BookingFactory.getPermissions();
	
	vm.logout = () => {
		BookingFactory.logout();
		$window.location.reload();
	}

});