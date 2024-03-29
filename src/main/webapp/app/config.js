var booking = angular.module("booking", ["ngRoute"]);

booking.config(['$locationProvider', function($locationProvider) {
	  $locationProvider.hashPrefix('');
}]);

booking.config(function ($routeProvider) {
    "use strict";
    $routeProvider
    	.when('/', {
    		templateUrl: "app/components/home/home.html"
    	})
	    .when('/newreservation', {
	        templateUrl: "app/components/reservation/newReservationView.html",
	        controller: "NewReservationController",
	        controllerAs: "vm"
	    })
	    .when('/newhotel', {
	        templateUrl: "app/components/hotel/newHotelView.html",
	        controller: "NewHotelController",
	        controllerAs: "vm"
	    })
	    .when('/newroom', {
	        templateUrl: "app/components/room/newRoomView.html",
	        controller: "NewRoomController",
	        controllerAs: "vm"
	    })
	    .when('/login', {
	        templateUrl: "app/components/login/loginView.html",
	        controller: "LoginController",
	        controllerAs: "vm"
	    })
	    .when('/search', {
	        templateUrl: "app/components/search/searchView.html",
	        controller: "SearchController",
	        controllerAs: "vm"
	    })
	    .when('/availabilitiesAddition', {
	    	templateUrl: "app/components/availability/availability.html",
		    controller: "AvailabilityController",
		    controllerAs: "vm"
	    })
        .otherwise({
            redirectTo: '/'
        });

});