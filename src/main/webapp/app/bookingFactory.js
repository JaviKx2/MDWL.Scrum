booking.factory('BookingFactory', function($rootScope, $http, $q, $window, DATE_FORMAT, INPUT_DATETIME_MIN_MAX_FORMAT){
	
	vm = this;
	vm.availability = {};
	vm.selectedAvailability = false;
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
	};
	
	var isLoggedIn = () => {
		if($window.localStorage['spring-token']) {
			return true;
		} else {
			return false;
		}
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
	
	var formatDate = (date) => {
		var dateObject = new Date(date);
		return moment.utc(dateObject).format(DATE_FORMAT);
	};
	
	var formatToMinMaxDatetime = (dateString) => {
		return moment.utc(dateString, DATE_FORMAT).format(INPUT_DATETIME_MIN_MAX_FORMAT);
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
		clearRedirectAfterLogin: clearRedirectAfterLogin,
		formatDate: formatDate,
		formatToMinMaxDatetime: formatToMinMaxDatetime
	};
});