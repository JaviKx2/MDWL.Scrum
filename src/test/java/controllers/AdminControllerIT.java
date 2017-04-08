package controllers;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import daos.core.AvailabilityDao;
import daos.core.HotelChainDao;
import daos.core.HotelDao;
import daos.core.ReservationDao;
import daos.core.RoomDao;
import daos.users.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class AdminControllerIT {

    @Autowired
    AdminController adminController;

    @Autowired
    private UserDao userDao;

    @Autowired
    private HotelChainDao hotelChainDao;

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private AvailabilityDao availabilityDao;

    @Test
    public void testDeleteAllExceptADmin() throws ParseException {
        adminController.deleteAllExceptAdmin();
        assertEquals(1, userDao.count());
        assertEquals(0, hotelChainDao.count());
        assertEquals(0, hotelDao.count());
        assertEquals(0, roomDao.count());
        assertEquals(0, reservationDao.count());
        assertEquals(0, availabilityDao.count());
        adminController.populate();
        assertNotEquals(0, userDao.count());
        assertNotEquals(0, hotelChainDao.count());
        assertNotEquals(0, hotelDao.count());
        assertNotEquals(0, roomDao.count());
        assertNotEquals(0, reservationDao.count());
        assertNotEquals(0, availabilityDao.count());
    }
}