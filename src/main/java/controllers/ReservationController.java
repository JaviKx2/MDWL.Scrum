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
import daos.users.UserDao;
import entities.core.Availability;
import entities.core.Reservation;
import entities.core.Room;
import entities.users.User;
import wrappers.ReservationPostWrapper;
import wrappers.ReservationWrapper;

@Controller
public class ReservationController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private AvailabilityDao availabilityDao;

    public ReservationWrapper add(ReservationPostWrapper reservationWrapper) {
        Reservation reservationAdded = null;
        if (reservationWrapper.getDepartureDate().before(reservationWrapper.getEntryDate())) {
            return null;
        }
        User user = userDao.findOne(reservationWrapper.getUserId());
        Room room = roomDao.findOne(reservationWrapper.getRoomId());
        String code = reservationWrapper.getRoomId() + System.currentTimeMillis() + "";
        Date entryDate = reservationWrapper.getEntryDate();
        Date departureDate = reservationWrapper.getDepartureDate();
        Reservation reservation = new Reservation(code, entryDate, departureDate, reservationWrapper.getNumberOfPeople(), room, user);
        List<Availability> availabilityList = availabilityDao.findIfRoomIsAvailable(room.getId(), entryDate, departureDate);
        if (!availabilityList.isEmpty()) {
            Availability availability = availabilityList.get(0);
            if (!availability.getStartDate().equals(entryDate)) {
                int hoursLeft = Hours.hoursBetween(new DateTime(availability.getStartDate()), new DateTime(reservation.getEntryDate()))
                        .getHours();
                if (hoursLeft > 2) {
                    DateTime endingDateDT = new DateTime(new DateTime(entryDate)).minusHours(2);
                    Availability availabilityBefore = new Availability(room, availability.getStartDate(), endingDateDT.toDate());
                    availabilityDao.save(availabilityBefore);
                }
            }
            if (!availability.getEndingDate().equals(departureDate)) {
                int hoursLeft = Hours.hoursBetween(new DateTime(departureDate), new DateTime(availability.getEndingDate())).getHours();
                if (hoursLeft > 2) {
                    DateTime startDateDT = new DateTime(departureDate).plusHours(2);
                    Availability availabilityAfter = new Availability(room, startDateDT.toDate(), availability.getEndingDate());
                    availabilityDao.save(availabilityAfter);
                }
            }
            reservationAdded = reservationDao.save(reservation);
            availabilityDao.delete(availability);
        }
        return new ReservationWrapper(reservationAdded);
    }

}
