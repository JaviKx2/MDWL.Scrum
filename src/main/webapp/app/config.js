var booking = angular.module("booking", ["ngRoute"]);

booking.config(function ($routeProvider) {
    "use strict";
    $routeProvider
	    .when('/newreservation', {
	        templateUrl: "app/components/reservation/newReservationView.html",
	        controller: "NewReservationController",
	        controllerAs: "vm"
	    })
	    .when('/login', {
	        templateUrl: "app/components/login/loginView.html",
	        controller: "LoginController",
	        controllerAs: "vm"
	    })
        .otherwise({
            redirectTo: '/'
        });

});