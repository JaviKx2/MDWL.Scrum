booking.service('SearchService', function ($location, API_BASE_URL, BookingFactory) {
   "use strict"; 
	
	this.getAvailableRooms = (searchValues) => {
		  let config = {
		 	 method: 'GET',
		 	 params: searchValues,
		 	 url: `${API_BASE_URL}/search`
		  };
	      return BookingFactory.request(config);
	};
	
	this.selectAvailability = (availability) => {
		BookingFactory.setAvailability(availability);
		$location.path("/newreservation");
	};

});