booking.service('SearchService', function (API_BASE_URL, BookingFactory) {
   "use strict"; 
	
	this.getAvailableRooms = (searchValues) => {
		  let config = {
		 	 method: 'GET',
		 	 params: searchValues,
		 	 url: `${API_BASE_URL}/search`
		  };
	      return BookingFactory.request(config);
	}

});