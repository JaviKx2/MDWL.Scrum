booking.controller('NewRoomController', function($route, AvailabilityService, RoomService, HotelService) {
	"use strict";
	
	var vm = this;
	
	vm.loading = false;
	vm.error = false;
	vm.types = ["tipo 1", "tipo 2", "tipo 3"];
	vm.roomType = vm.types[0];

	
	vm.onClickSubmitButton = () => {
		vm.room = {
				price: vm.price,
				capacity: vm.capacity,
				services: vm.services,
				number: vm.number
		}
		RoomService.addRoom(vm.room).then(result => {
			vm.loading = false;	
			vm.error = false;
		}, errors => {
			vm.loading = false;
			vm.error = true;
		});
	}
	
	vm.listHotels = () => {
		HotelService.findAll().then(function success(response) {
			console.log(response);
			vm.hotels = response;
			vm.hotel = vm.hotels[0].name;
		}, function error(errors) {
			console.log(errors);
		});
	}

		
	vm.reload = () => {
		$route.reload();
	}
		
});