package api;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import daos.core.RoomDao;
import daos.users.TokenDao;
import daos.users.UserDao;
import entities.core.Room;
import entities.users.User;
import wrappers.ReservationPostWrapper;
import wrappers.ReservationWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class ReservationResourceFunctionalTesting {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy HH:mm");

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoomDao roomDao;
    
    @Autowired
    private TokenDao tokenDao;
    
    @Test(expected = HttpClientErrorException.class)
    public void testWithoutHeader() throws ParseException {
        Date entryDate = sdf.parse("31-08-2017 14:20");
        Date departureDate = sdf.parse("31-08-2017 17:20");
        Room room = roomDao.findAll().get(0);
        User user = userDao.findAll().get(2);
        String tokenValue = tokenDao.findByUser(user).getValue();
        ReservationPostWrapper reservationWrapper = new ReservationPostWrapper(entryDate, departureDate, room.getId(), user.getId(), 1);
        ReservationWrapper reservation = new RestBuilder<ReservationWrapper>(RestService.URL).path(Uris.RESERVATIONS)
                .body(reservationWrapper).clazz(ReservationWrapper.class).post().build();
    }
    
    @Test
    public void testBadToken() throws ParseException {
        Date entryDate = sdf.parse("31-08-2017 14:20");
        Date departureDate = sdf.parse("31-08-2017 17:20");
        Room room = roomDao.findAll().get(0);
        User user = userDao.findAll().get(2);
        ReservationPostWrapper reservationWrapper = new ReservationPostWrapper(entryDate, departureDate, room.getId(), user.getId(), 1);
        ReservationWrapper reservation = new RestBuilder<ReservationWrapper>(RestService.URL).path(Uris.RESERVATIONS)
                .body(reservationWrapper).header("x-access-token", "BAD TOKEN").clazz(ReservationWrapper.class).post().build();
        assertNull(reservation);
    }
    
    @Test
    public void testBadPermission() throws ParseException {
        Date entryDate = sdf.parse("31-08-2017 14:20");
        Date departureDate = sdf.parse("31-08-2017 17:20");
        Room room = roomDao.findAll().get(0);
        User user = userDao.findAll().get(3);
        String tokenValue = tokenDao.findByUser(user).getValue();
        ReservationPostWrapper reservationWrapper = new ReservationPostWrapper(entryDate, departureDate, room.getId(), user.getId(), 1);
        ReservationWrapper reservation = new RestBuilder<ReservationWrapper>(RestService.URL).path(Uris.RESERVATIONS)
                .body(reservationWrapper).header("x-access-token", tokenValue).clazz(ReservationWrapper.class).post().build();
        assertNull(reservation);
    }
   
    @Test
    public void testAddReservation() throws ParseException {
        Date entryDate = sdf.parse("31-08-2017 14:20");
        Date departureDate = sdf.parse("31-08-2017 17:20");
        Room room = roomDao.findAll().get(0);
        User user = userDao.findAll().get(2);
        String tokenValue = tokenDao.findByUser(user).getValue();
        ReservationPostWrapper reservationWrapper = new ReservationPostWrapper(entryDate, departureDate, room.getId(), user.getId(), 1);
        ReservationWrapper reservation = new RestBuilder<ReservationWrapper>(RestService.URL).path(Uris.RESERVATIONS)
                .body(reservationWrapper).header("x-access-token", tokenValue).clazz(ReservationWrapper.class).post().build();
        assertEquals(sdf.format(sdf.parse("31-08-2017 14:20")), reservation.getEntryDate());
        assertEquals(sdf.format(sdf.parse("31-08-2017 17:20")), reservation.getDepartureDate());
        assertEquals(3, reservation.getHours());
        assertNotNull(reservation.getRoomId());
        assertEquals(1, reservation.getNumberOfPeople());
        assertEquals(33.0, reservation.getPrice(), 1.0);
    }

    @Test
    public void testAddReservationWithDatesInverted() throws ParseException {
        Date entryDate = sdf.parse("31-08-2017 16:20");
        Date departureDate = sdf.parse("31-08-2017 14:20");
        User user = userDao.findAll().get(2);
        String tokenValue = tokenDao.findByUser(user).getValue();
        ReservationPostWrapper reservationWrapper = new ReservationPostWrapper(entryDate, departureDate, 0, user.getId(), 1);
        ReservationWrapper reservation = new RestBuilder<ReservationWrapper>(RestService.URL).path(Uris.RESERVATIONS)
                .body(reservationWrapper).header("x-access-token", tokenValue).clazz(ReservationWrapper.class).post().build();
        assertNull(reservation);
    }

    @Test
    public void testAddReservationWithDatesNotValid() throws ParseException {
        Date entryDate = sdf.parse("31-08-2017 20:20");
        Date departureDate = sdf.parse("31-08-2017 19:20");
        Room room = roomDao.findAll().get(0);
        User user = userDao.findAll().get(2);
        String tokenValue = tokenDao.findByUser(user).getValue();
        ReservationPostWrapper reservationWrapper = new ReservationPostWrapper(entryDate, departureDate, room.getId(), user.getId(), 1);
        ReservationWrapper reservation = new RestBuilder<ReservationWrapper>(RestService.URL).path(Uris.RESERVATIONS)
                .body(reservationWrapper).header("x-access-token", tokenValue).post().clazz(ReservationWrapper.class).build();
        assertNull(reservation);
    }

}
