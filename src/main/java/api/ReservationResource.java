package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.ReservationController;
import controllers.TokenController;
import entities.users.Permissions;
import wrappers.ReservationPostWrapper;
import wrappers.ReservationWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.RESERVATIONS)
public class ReservationResource {

    @Autowired
    private ReservationController reservationController;
    
    @Autowired
    private TokenController tokenController;

    @RequestMapping(method = RequestMethod.POST)
    public ReservationWrapper add(@RequestBody ReservationPostWrapper reservationPostWrapper, @RequestHeader("x-access-token") String token) {
        if (tokenController.userHasPermission(token, Permissions.BASIC)){
            return reservationController.add(reservationPostWrapper);
        } else {
            return null;
        }
    }

}
