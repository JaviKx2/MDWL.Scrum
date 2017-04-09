package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.ReservationController;
import wrappers.ReservationPostWrapper;
import wrappers.ReservationWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.RESERVATIONS)
public class ReservationResource {

    @Autowired
    private ReservationController reservationController;

    @RequestMapping(method = RequestMethod.POST)
    public ReservationWrapper add(@RequestBody ReservationPostWrapper reservationPostWrapper) {
        return reservationController.add(reservationPostWrapper);
    }

}
