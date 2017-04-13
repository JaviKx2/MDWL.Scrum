booking.service('loginService', function ($http, $q, $window) {
   "use strict"; 
   
   	const urlBase = "http://localhost:8080/Booking.1.0.0-SNAPSHOT/api/v0";
   		
	this.request = config => {
	      let deferred = $q.defer();
	      $http(config).then(response => {
	    	  if (response.data.token){ 
	    		  $window.localStorage['spring-token'] = response.data.token;
		          $http.defaults.headers.common['x-access-token'] = response.data.token;
		      }
	    	  deferred.resolve(response.data);
	      }, response => {
	    	  deferred.reject(`Error `);
	      });
	      return deferred.promise;	   
	}	
   
	this.login = loginData => {
		  let config = {
		 	 method: 'POST',
		 	 url: `${urlBase}/login`,
		 	 data: {
		 		 email: loginData.email,
		 		 password: loginData.password
		 	 }
		  };
	      return this.request(config);
	}

});