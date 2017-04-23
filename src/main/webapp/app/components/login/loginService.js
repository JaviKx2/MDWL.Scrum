booking.service('loginService', function (API_BASE_URL, BookingFactory) {
   "use strict";
   
	this.login = (loginData) => {
		  let config = {
		 	 method: 'POST',
		 	 url: `${API_BASE_URL}/login`,
		 	 data: {
		 		 email: loginData.email,
		 		 password: loginData.password
		 	 }
		  };
	      return BookingFactory.request(config);
	};

});