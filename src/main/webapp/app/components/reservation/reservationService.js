booking.service('reservationService', function ($http, $q) {
   "use strict"; 
   
   	const urlBase = "http://localhost:8080/Booking.1.0.0-SNAPSHOT/api/v0";
   		
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
   
	this.add = reservation => {
		  let config = {
		 	 method: 'POST',
		 	 url: `${urlBase}/reservations`,
		 	 data: {
		 		 entryDate: reservation.entryDate,
		 		 departureDate: reservation.departureDate,
		 		 numberOfPeople: reservation.numberOfPeople,
		 		 roomId: reservation.roomId,
		 		 userId: reservation.userId
		 	 }
		  };
	      return this.request(config);
	}

});