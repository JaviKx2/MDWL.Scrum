booking.service('HotelChainService', function (API_BASE_URL, BookingFactory) {
   "use strict";
   
	this.findAll = () => {
		  let config = {
		 	 method: 'GET',
		 	 url: `${API_BASE_URL}/hotel_chain`,
		  };
	      return BookingFactory.request(config);
	}

});