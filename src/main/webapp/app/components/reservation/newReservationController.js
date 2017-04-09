booking.controller('NewReservationController', function($route, reservationService) {
	"use strict";
	
	var vm = this;
	
	vm.newReservation = {};
	vm.loading = false;
	vm.error = false;
	
	vm.submitReservation = () => {
 		reservationService.add(vm.newReservation).then(result => {
			vm.loading = false;	
			vm.error = false;
			vm.reservationData = result;
		}, errors => {
			vm.loading = false;
			vm.error = true;
		});
		vm.newReservation = {};
	}
	
	vm.reload = () => {
		$route.reload();
	}
		
});