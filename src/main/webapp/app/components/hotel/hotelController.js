booking.controller('NewHotelController', function($route, HotelChainService, HotelService, UserService) {
	"use strict";
	
	var vm = this;
	
	vm.hotelChain = {};
	vm.hotelChains = [];
	vm.user = {};
	vm.users = [];
	vm.hotel = {};
	vm.name = "";
	vm.postcode = "";
	vm.city = "";
	vm.image = "";
	
	vm.loading = false;
	vm.error = false;
	
	vm.onClickSubmitButton = () => {
		vm.hotel = {
				name: vm.name,
				postcode: vm.postcode,
				city: vm.city,
				hotelChainId: vm.hotelChain.id,
				manager: vm.user,
				image: vm.image
		}
		console.log(vm.hotel);
 		HotelService.add(vm.hotel).then(result => {
			vm.loading = false;	
			vm.error = false;
		}, errors => {
			vm.loading = false;
			vm.error = true;
		});
	}
	
	vm.listHotelChains = () => {
		HotelChainService.findAll().then(function success(response) {
			console.log(response);
			vm.hotelChains = response;
			vm.hotelChain = vm.hotelChains[0];
		}, function error(errors) {
			console.log(errors);
		});
	}

	vm.listUsers = () => {
		UserService.findAllUsers().then(function success(response) {
			console.log(response);
			vm.users = response;
			vm.user = vm.user[0];
		}, function error(errors) {
			console.log(errors);
		});
	}
		
	vm.reload = () => {
		$route.reload();
	}
		
});