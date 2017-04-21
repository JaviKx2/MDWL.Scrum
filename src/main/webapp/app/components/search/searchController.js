booking.controller('SearchController', function($route, SearchService) {
	"use strict";
	
	var vm = this;
	
	vm.searchValues = {};
	
	vm.loading = false;
	vm.error = false;
	
	vm.availableRooms = [];
	
	function loadAvailableRooms(){
		SearchService.getAvailableRooms(vm.searchValues).then(result => {
			vm.loading = false;
			vm.availableRooms = result;
			vm.error = false;
		}, errors => {
			vm.loading = false;
			vm.error = true;
		});
	}
	
	vm.onClickSearchButton = () => {
		vm.loading = true;
		loadAvailableRooms();
	}
	
	vm.clearFilters = () => {
		$route.reload();
	}
		
});