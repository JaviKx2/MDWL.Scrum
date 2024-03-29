booking.service('RoomService', function (API_BASE_URL, BookingFactory) {
   "use strict";
   
	this.findAllRooms = () => {
		  let config = {
		 	 method: 'GET',
		 	 url: `${API_BASE_URL}/rooms`,
		  };
	      return BookingFactory.request(config);
	}
	
	this.addRoom = room => {
		  let config = {
		 	 method: 'POST',
		 	 url: `${API_BASE_URL}/rooms`,
		 	 data: room
		  };
	      return BookingFactory.request(config);
	}

});