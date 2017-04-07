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
import entities.core.Availability;
import entities.core.Room;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class AvailabilityDaoIT {

    @Autowired
    private AvailabilityDao availabilityDao;

    @Autowired
    private RoomDao roomDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");

    @Test
    public void testCountAvailability() {
        assertEquals(4, availabilityDao.count());
    }

    @Test
    public void testOneAvailability() throws ParseException {
        Availability availability = availabilityDao.findAll().get(0);
        assertEquals(sdf.parse("31-08-2017 10:20"), availability.getStartDate());
        assertEquals(sdf.parse("31-08-2017 20:20"), availability.getEndingDate());
        assertEquals(1, availability.getRoom().getNumber());
    }

    @Test
    public void testFindIfRoomIsAvailable() throws ParseException {
        Room room = roomDao.findAll().get(0);
        assertEquals(1,
                availabilityDao.findIfRoomIsAvailable(room.getId(), sdf.parse("31-08-2017 11:20"), sdf.parse("31-08-2017 19:20")).size());
        assertTrue(availabilityDao.findIfRoomIsAvailable(room.getId(), sdf.parse("31-08-2017 11:20"), sdf.parse("31-08-2017 23:20"))
                .isEmpty());
    }

}
