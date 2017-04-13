package daos.core;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.Hotel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class HotelDaoIT {

    @Autowired
    private HotelDao hotelDao;

    @Test
    public void testCountHotel() {
        assertEquals(20, hotelDao.count());
    }

    @Test
    public void testOneHotel() {
        Hotel hotel = hotelDao.findAll().get(0);
        assertEquals("city0", hotel.getCity());
        assertEquals("image0", hotel.getImage());
        assertEquals("hotel0", hotel.getName());
        assertEquals("postcode0", hotel.getPostcode());
        assertEquals("hotelchain0", hotel.getHotelChain().getName());
        assertEquals("receptionist", hotel.getManager().getName());
    }

}
