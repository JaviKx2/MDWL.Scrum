package controllers;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.AvailabilityDao;
import daos.core.ReservationDao;
import daos.core.RoomDao;
import entities.core.Availability;
import entities.core.Reservation;
import entities.core.Room;
import entities.users.User;
import wrappers.ReservationCreationWrapper;
import wrappers.ReservationWrapper;

@Controller
public class ReservationController {

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private AvailabilityDao availabilityDao;

    public ReservationWrapper add(ReservationCreationWrapper reservationWrapper, User user) {
        Reservation reservationAdded = null;
        Date entryDate = reservationWrapper.getEntryDate();
        Date departureDate = reservationWrapper.getDepartureDate();
        Room room = roomDao.findOne(reservationWrapper.getRoomId());
        if (user == null || room == null || departureDate.before(entryDate)) {
            return null;
        }

        List<Availability> availabilityList = availabilityDao.findIfRoomIsAvailable(room.getId(), entryDate, departureDate);
        if (!availabilityList.isEmpty()) {
            Availability availability = availabilityList.get(0);

            int hoursLeftBefore = Hours.hoursBetween(new DateTime(availability.getStartDate()), new DateTime(entryDate)).getHours();
            if (hoursLeftBefore > 2) {
                DateTime endingDateDT = new DateTime(entryDate).minusHours(2);
                Availability availabilityBefore = new Availability(room, availability.getStartDate(), endingDateDT.toDate());
                availabilityDao.save(availabilityBefore);
            }

            int hoursLeftAfter = Hours.hoursBetween(new DateTime(departureDate), new DateTime(availability.getEndingDate())).getHours();
            if (hoursLeftAfter > 2) {
                DateTime startDateDT = new DateTime(departureDate).plusHours(2);
                Availability availabilityAfter = new Availability(room, startDateDT.toDate(), availability.getEndingDate());
                availabilityDao.save(availabilityAfter);
            }
            availabilityDao.delete(availability);

            Reservation reservation = new Reservation(entryDate, departureDate, reservationWrapper.getNumberOfPeople(), room, user);
            reservationAdded = reservationDao.save(reservation);
        }
        return new ReservationWrapper(reservationAdded);
    }

}
