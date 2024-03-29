package controllers;

import static config.Constants.DATE_FORMAT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import daos.core.AvailabilityDao;
import daos.core.RoomDao;
import daos.users.UserDao;
import entities.core.Room;
import entities.users.User;
import services.DatabaseSeeder;
import wrappers.ReservationCreationWrapper;
import wrappers.ReservationWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class ReservationControllerIT {

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AvailabilityDao availabilityDao;

    @Autowired
    private ReservationController reservationController;

    private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    @Autowired
    private DatabaseSeeder databaseSeeder;

    @Test
    public void testReservationWithTwoNewAvailabilities() throws ParseException {
        Room room = roomDao.findAll().get(0);
        User user = userDao.findAll().get(1);
        ReservationCreationWrapper reservationWrapper = new ReservationCreationWrapper(sdf.parse("31-08-2017 14:20"), sdf.parse("31-08-2017 17:20"),
                room.getId(), 1);
        ReservationWrapper reservation = reservationController.add(reservationWrapper, user);
        assertNotNull(reservation);
        assertEquals(sdf.format(sdf.parse("31-08-2017 14:20")), reservation.getEntryDate());
        assertEquals(sdf.format(sdf.parse("31-08-2017 17:20")), reservation.getDepartureDate());
        assertEquals(3, reservation.getHours());
        assertNotNull(reservation.getRoomId());
        assertEquals(1, reservation.getNumberOfPeople());
        assertEquals(33.0, reservation.getPrice(), 1.0);
        assertTrue(availabilityDao.findByRoomAndStartDate(room, sdf.parse("31-08-2017 14:20")).isEmpty());
        assertTrue(availabilityDao.findIfRoomIsAvailable(room.getId(), sdf.parse("31-08-2017 16:20"), sdf.parse("31-08-2017 19:20"))
                .isEmpty());
        assertFalse(availabilityDao.findByRoomAndStartDate(room, sdf.parse("31-08-2017 10:20")).isEmpty());
        Date endingDateFirstNewAvailability = availabilityDao.findByRoomAndStartDate(room, sdf.parse("31-08-2017 10:20")).get(0)
                .getEndingDate();
        assertEquals(sdf.parse("31-08-2017 12:20"), endingDateFirstNewAvailability);
        assertFalse(availabilityDao.findByRoomAndStartDate(room, sdf.parse("31-08-2017 19:20")).isEmpty());
        assertEquals(sdf.parse("31-08-2017 20:20"),
                availabilityDao.findByRoomAndStartDate(room, sdf.parse("31-08-2017 19:20")).get(0).getEndingDate());
    }

    @Test
    public void testReservationWithNewAvailabilityBefore() throws ParseException {
        Room room = roomDao.findAll().get(1);
        User user = userDao.findAll().get(1);
        ReservationCreationWrapper reservationWrapper = new ReservationCreationWrapper(sdf.parse("31-08-2017 14:20"), sdf.parse("31-08-2017 20:20"),
                room.getId(), 1);
        ReservationWrapper reservation = reservationController.add(reservationWrapper, user);
        assertNotNull(reservation);
        assertEquals(sdf.format(sdf.parse("31-08-2017 14:20")), reservation.getEntryDate());
        assertEquals(sdf.format(sdf.parse("31-08-2017 20:20")), reservation.getDepartureDate());
        assertEquals(6, reservation.getHours());
        assertNotNull(reservation.getRoomId());
        assertEquals(1, reservation.getNumberOfPeople());
        assertEquals(72.0, reservation.getPrice(), 1.0);
        assertTrue(availabilityDao.findByRoomAndStartDate(room, sdf.parse("31-08-2017 14:20")).isEmpty());
        assertTrue(availabilityDao.findIfRoomIsAvailable(room.getId(), sdf.parse("31-08-2017 16:20"), sdf.parse("31-08-2017 19:20"))
                .isEmpty());
        assertFalse(availabilityDao.findByRoomAndStartDate(room, sdf.parse("31-08-2017 11:20")).isEmpty());
        assertEquals(sdf.parse("31-08-2017 12:20"),
                availabilityDao.findByRoomAndStartDate(room, sdf.parse("31-08-2017 11:20")).get(0).getEndingDate());
        assertTrue(availabilityDao.findByRoomAndEndingDate(room, sdf.parse("31-08-2017 21:20")).isEmpty());
    }

    @Test
    public void testReservationWithNewAvailabilityAfter() throws ParseException {
        Room room = roomDao.findAll().get(2);
        User user = userDao.findAll().get(1);
        ReservationCreationWrapper reservationWrapper = new ReservationCreationWrapper(sdf.parse("31-08-2017 12:20"), sdf.parse("31-08-2017 17:20"),
                room.getId(), 1);
        ReservationWrapper reservation = reservationController.add(reservationWrapper, user);
        assertNotNull(reservation);
        assertEquals(sdf.format(sdf.parse("31-08-2017 12:20")), reservation.getEntryDate());
        assertEquals(sdf.format(sdf.parse("31-08-2017 17:20")), reservation.getDepartureDate());
        assertEquals(5, reservation.getHours());
        assertNotNull(reservation.getRoomId());
        assertEquals(1, reservation.getNumberOfPeople());
        assertEquals(65.0, reservation.getPrice(), 1.0);
        assertTrue(availabilityDao.findByRoomAndStartDate(room, sdf.parse("31-08-2017 14:20")).isEmpty());
        assertTrue(availabilityDao.findIfRoomIsAvailable(room.getId(), sdf.parse("31-08-2017 16:20"), sdf.parse("31-08-2017 19:20"))
                .isEmpty());
        assertTrue(availabilityDao.findByRoomAndStartDate(room, sdf.parse("31-08-2017 11:20")).isEmpty());
        assertFalse(availabilityDao.findByRoomAndStartDate(room, sdf.parse("31-08-2017 19:20")).isEmpty());
        assertEquals(sdf.parse("31-08-2017 22:20"),
                availabilityDao.findByRoomAndStartDate(room, sdf.parse("31-08-2017 19:20")).get(0).getEndingDate());
    }

    /**
     * Leave database as it was before tests
     * 
     * @throws ParseException
     */
    @After
    public void tearDown() throws ParseException {
        databaseSeeder.deleteAllExceptAdmin();
        databaseSeeder.populate();
    }

}
