booking.controller('NewHotelController', function($route, HotelChainService, HotelService) {
	"use strict";
	
	var vm = this;
	
	vm.hotelChain = {};
	vm.hotelChains = [];
	vm.hotel = {};
	vm.name = "";
	vm.postcode = "";
	vm.city = "";
	
	vm.loading = false;
	vm.error = false;
	
	vm.onClickSubmitButton = () => {
		vm.hotel = {
				name: vm.name,
				postcode: vm.postcode,
				city: vm.city,
				hotelChain : vm.hotelChain
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
			vm.hotelChain = vm.hotelChains[0].name;
		}, function error(errors) {
			console.log(errors);
		});
	}
		
	vm.reload = () => {
		$route.reload();
	}
		
});