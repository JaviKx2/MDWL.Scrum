package entities.core;

import static config.Constants.DATE_FORMAT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import entities.users.Permissions;
import entities.users.User;

public class ReservationTest {

    private Reservation reservation;

    private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    @Before
    public void before() throws ParseException {
        Room room = new Room(101, RoomType.SINGLE, 15.5, 1, null, "Jacuzzi, Internet");
        Date entryDate = sdf.parse("31-08-2017 10:20");
        Date departureDate = sdf.parse("04-09-2017 20:40");
        User user = new User("mdw@upm.es", "12341234", "Rodrigo", "García", Permissions.BASIC);
        reservation = new Reservation(entryDate, departureDate, 1, room, user);
    }

    @Test
    public void testReservationCreation() throws ParseException {
        assertEquals(sdf.parse("31-08-2017 10:20"), reservation.getEntryDate());
        assertEquals(sdf.parse("04-09-2017 20:40"), reservation.getDepartureDate());
        assertEquals(1, reservation.getNumberOfPeople());
        assertNotNull(reservation.getRoom());
        assertNotNull(reservation.getUser());
    }

    @Test
    public void testHours() {
        assertEquals(106, reservation.getHours());
    }

    @Test
    public void testPrice() {
        assertEquals(1643.0, reservation.getPrice(), 0.01);
    }

}
