booking.service('HotelService', function (API_BASE_URL, BookingFactory) {
   "use strict";
   
	this.findAll = () => {
		  let config = {
		 	 method: 'GET',
		 	 url: `${API_BASE_URL}/hotel`,
		  };
	      return BookingFactory.request(config);
	}
   
	this.add = hotel => {
		  let config = {
		 	 method: 'POST',
		 	 url: `${API_BASE_URL}/hotel`,
		 	 data: hotel
		  };
	      return BookingFactory.request(config);
	}

});