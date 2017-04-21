booking.factory('BookingFactory', function($rootScope, $http, $q){
	
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
	
	return {
		request: request
	};
});