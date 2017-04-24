booking.controller('NewRoomController', function($route, AvailabilityService, RoomService, HotelService) {
	"use strict";
	
	var vm = this;
	
	vm.loading = false;
	vm.error = false;
	vm.types = ["SINGLE", "DOUBLE", "TRIPLE", "SUITE"];
	vm.roomType = vm.types[0];
	vm.hotels = [];
	vm.hotel = {};

	
	vm.onClickSubmitButton = () => {
		vm.room = {
				hotel: vm.hotel,
				price: vm.price,
				capacity: vm.capacity,
				services: vm.services,
				number: vm.number,
				type: vm.roomType
		};
		console.log(vm.room);
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
			vm.hotel = vm.hotels[0];
		}, function error(errors) {
			console.log(errors);
		});
	}

		
	vm.reload = () => {
		$route.reload();
	}
		
});