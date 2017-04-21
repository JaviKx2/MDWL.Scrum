booking.service('SearchService', function ($http, $q, API_BASE_URL) {
   "use strict"; 
	
	this.request = config => {
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
	}
   
	this.getAvailableRooms = (searchValues) => {
		  let config = {
		 	 method: 'GET',
		 	 params: searchValues,
		 	 url: `${API_BASE_URL}/search`
		  };
	      return this.request(config);
	}

});