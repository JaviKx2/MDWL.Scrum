booking.controller('AvailabilityController', function($route, AvailabilityService, RoomService) {
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
		vm.availability = {
				roomId: vm.room.id,
				slotStartDate: vm.slotStartDate,
				slotEndDate: vm.slotEndDate
		}
 		AvailabilityService.add(vm.availability).then(result => {
			vm.loading = false;	
			vm.error = false;
		}, errors => {
			vm.loading = false;
			vm.error = true;
		});
	}
	
	vm.listRooms = () => {
		RoomService.findAllRooms().then(function success(response) {
			console.log(response);
			vm.rooms = response;
			vm.room = vm.rooms[0];
		}, function error(errors) {
			console.log(errors);
		});
	}
		
	vm.reload = () => {
		$route.reload();
	}
		
});