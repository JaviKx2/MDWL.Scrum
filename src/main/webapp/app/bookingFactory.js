booking.factory('BookingFactory', function($rootScope, $http, $q){
	
	vm = this;
	vm.availability = {};
	vm.selectedAvailability = false;
	
	var request = config => {
	      let deferred = $q.defer();
	      $http(config).then(response => {
	    	  deferred.resolve(response.data);
	      }, response => {
	    	  let errorMsg;
	    	  if(response.data.error === undefined) {
	    		  errorMsg="";
	    	  }else{
	    		  errorMsg = ` --- ${response.data.error}:${response.data.description}`;
	    	  }
	    	  deferred.reject( 
	    	  	`Error (${response.status}:${response.statusText})${errorMsg}`);
	      });
	      return deferred.promise;	   
	};
	
	var setAvailability = (availability) => {
		vm.availability = availability;
		vm.selectedAvailability = true;
	};
	
	var getAvailability = () => {
		return vm.availability;
	};
	
	var isAvailabilitySelected = () => {
		return vm.selectedAvailability;
	};
	
	var clearAvailabilty = () => {
		vm.availability = {};
		vm.selectedAvailability = false;
	};
	
	return {
		request: request,
		setAvailability: setAvailability,
		getAvailability: getAvailability,
		isAvailabilitySelected: isAvailabilitySelected,
		clearAvailabilty: clearAvailabilty
	};
});