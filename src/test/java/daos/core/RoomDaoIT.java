package daos.core;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.Room;
import entities.core.RoomType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class RoomDaoIT {

    @Autowired
    private RoomDao roomDao;

    @Test
    public void testCountRooms() {
        assertEquals(40, roomDao.count());
    }

    @Test
    public void testOneRoom() {
        Room room = roomDao.findAll().get(0);
        assertEquals(1, room.getNumber());
        assertEquals(11.0, room.getPrice(), 0.01);
        assertNotNull(room.getServices());
        assertEquals("city1", room.getHotel().getCity());
        assertEquals(1, room.getCapacity());
        assertEquals(RoomType.SINGLE, room.getType());
    }

}
