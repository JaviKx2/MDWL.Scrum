booking.factory('BookingFactory', function($rootScope, $http, $q, $window){
	
	vm = this;
	vm.availability = {};
	vm.selectedAvailability = false;
	vm.loggedIn = false;
	vm.redirectAfterLogin = '';
	
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
	
	var saveToken = (token) => {
		$window.localStorage['spring-token'] = token;
        $http.defaults.headers.common['x-access-token'] = token;
    	vm.loggedIn = true;
	};
	
	var isLoggedIn = () => {
		return vm.loggedIn;
	};
	
	var setRedirectAfterLogin = (redirectPath) => {
		vm.redirectAfterLogin = redirectPath;
	};
	
	var getRedirectAfterLogin = () => {
		return vm.redirectAfterLogin;
	};
	
	var clearRedirectAfterLogin = () => {
		vm.redirectAfterLogin = '';
	};
	
	return {
		request: request,
		setAvailability: setAvailability,
		getAvailability: getAvailability,
		isAvailabilitySelected: isAvailabilitySelected,
		clearAvailabilty: clearAvailabilty,
		saveToken: saveToken,
		isLoggedIn: isLoggedIn,
		setRedirectAfterLogin: setRedirectAfterLogin,
		getRedirectAfterLogin: getRedirectAfterLogin,
		clearRedirectAfterLogin: clearRedirectAfterLogin
	};
});