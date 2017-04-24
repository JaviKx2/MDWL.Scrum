booking.controller('NewReservationController', function($route, $location, reservationService, BookingFactory) {
	"use strict";
	
	var vm = this;
	
	vm.availabilitySelected = {};
	vm.newReservation = {};
	vm.loading = false;
	vm.error = false;
	
	var initialChecks = () => {
		if (BookingFactory.isLoggedIn()) {
			if (BookingFactory.isAvailabilitySelected()) {
				vm.availabilitySelected = BookingFactory.getAvailability();
				vm.newReservation.roomId = vm.availabilitySelected.roomId;
			} else {
				$location.path("/search");
			}
		} else {
			BookingFactory.setRedirectAfterLogin("/newreservation");
			$location.path("/login");
		}
	};
	
	initialChecks();
	
	vm.submitReservation = () => {
 		reservationService.add(vm.newReservation).then(result => {
			vm.loading = false;	
			if(result) {
				vm.error = false;
				vm.reservationData = result;
			} else {
				vm.error = true;
			}
			vm.newReservation = {};
		}, errors => {
			vm.loading = false;
			vm.error = true;
			vm.newReservation = {};
		});
	}
	
	vm.getMinReservationDate = () => {
		return BookingFactory.formatToMinMaxDatetime(vm.availabilitySelected.slotStartDate);
	};
	
	vm.getMaxReservationDate = () => {
		return BookingFactory.formatToMinMaxDatetime(vm.availabilitySelected.slotEndDate);
	};
	
	vm.clickOnOk = () => {
		$location.path("/search");
	}
});