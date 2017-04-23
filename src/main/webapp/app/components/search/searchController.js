booking.controller('SearchController', function($location, $route, SearchService, BookingFactory) {
	"use strict";
	
	var vm = this;
	
	vm.searchValues = {};
	
	vm.noSearch = true;
	vm.loading = false;
	vm.error = false;
	
	vm.availableRooms = [];
	
	var formatSearchValues = () => {
		var searchValues = {}
		searchValues.hotelName = vm.searchValues.hotelName;
		searchValues.city = vm.searchValues.city;
		searchValues.postalCode = vm.searchValues.postalCode;
		if (vm.searchValues.slotStartDate) {
			searchValues.slotStartDate = BookingFactory.formatDate(vm.searchValues.slotStartDate);
		}
		if (vm.searchValues.slotEndDate) {
			searchValues.slotEndDate = BookingFactory.formatDate(vm.searchValues.slotEndDate);
		}
		return searchValues;
	};
	
	function loadAvailableRooms() {
		var searchValues = formatSearchValues();
		SearchService.getAvailableRooms(searchValues).then(result => {
			vm.loading = false;
			vm.availableRooms = result;
			vm.error = false;
		}, errors => {
			vm.loading = false;
			vm.error = true;
		});
	};
	
	vm.onClickSearchButton = () => {
		vm.loading = true;
		if (vm.noSearch) {
			vm.noSearch = false;
		}
		loadAvailableRooms();
	};
	
	vm.bookRoom = (index) => {
		BookingFactory.setAvailability(vm.availableRooms[index]);
		$location.path("/newreservation");
	};
	
	vm.clearFilters = () => {
		$route.reload();
	};
		
});