booking.service('AvailabilityService', function (API_BASE_URL, BookingFactory) {
   "use strict";
   
	this.add = availability => {
		  let config = {
		 	 method: 'POST',
		 	 url: `${API_BASE_URL}/availabilities`,
		 	 data: {
		 		 roomId: availability.roomId,
		 		 slotStartDate: availability.slotStartDate,
		 		 slotEndDate: availability.slotEndDate,
		 	 }
		  };
	      return BookingFactory.request(config);
	}

});