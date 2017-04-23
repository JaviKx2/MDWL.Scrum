booking.service('reservationService', function (API_BASE_URL, BookingFactory) {
   "use strict";
   
	this.add = (reservation) => {
		  let config = {
		 	 method: 'POST',
		 	 url: `${API_BASE_URL}/reservations`,
		 	 data: {
		 		 entryDate: reservation.entryDate,
		 		 departureDate: reservation.departureDate,
		 		 numberOfPeople: reservation.numberOfPeople,
		 		 roomId: reservation.roomId
		 	 }
		  };
	      return BookingFactory.request(config);
	}

});