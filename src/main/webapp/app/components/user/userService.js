booking.service('UserService', function (API_BASE_URL, BookingFactory) {
   "use strict";
   
	this.findAllUsers = () => {
		  let config = {
		 	 method: 'GET',
		 	 url: `${API_BASE_URL}/user`,
		  };
	      return BookingFactory.request(config);
	}
});