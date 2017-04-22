booking.controller('AvailabilityController', function($route, AvailabilityService) {
	"use strict";
	
	var vm = this;
	
	vm.availability = {};
	vm.room = {}
	vm.rooms = [];
	vm.slotStartDate = {};
	vm.slotEndDate = {};
	vm.loading = false;
	vm.error = false;
	
	vm.onClickSubmitButton = () => {
 		AvailabilityService.add(vm.availability).then(result => {
			vm.loading = false;	
			vm.error = false;
		}, errors => {
			vm.loading = false;
			vm.error = true;
		});
	}
		
	vm.reload = () => {
		$route.reload();
	}
		
});