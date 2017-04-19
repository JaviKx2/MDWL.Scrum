package daos.core;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.Reservation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class ReservationDaoIT {

    @Autowired
    private ReservationDao reservationDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @Test
    public void testCountReservation() {
        assertEquals(5, reservationDao.count());
    }

    @Test
    public void testOneReservation() throws ParseException {
        Reservation reservation = reservationDao.findAll().get(0);
        assertEquals(2, reservation.getRoom().getNumber());
        assertEquals(1, reservation.getNumberOfPeople());
        assertEquals(sdf.parse("31-08-2017 01:20"), reservation.getEntryDate());
        assertEquals(sdf.parse("31-08-2017 11:20"), reservation.getDepartureDate());
        assertEquals(10, reservation.getHours());
        assertEquals("name20", reservation.getUser().getName());
        assertEquals("XXX1", reservation.getCode());
        assertEquals(120.0, reservation.getPrice(), 0.01);
    }

}
