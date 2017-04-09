package daos.core;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.HotelChain;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class HotelChainDaoIT {

    @Autowired
    private HotelChainDao hotelChainDao;

    @Test
    public void testCountHotelChain() {
        assertEquals(5, hotelChainDao.count());
    }

    @Test
    public void testOneHotelChain() {
        HotelChain hotelChain = hotelChainDao.findAll().get(0);
        assertEquals("hotelchain0", hotelChain.getName());
        assertEquals("name0", hotelChain.getManager().getName());
        assertEquals("logo0", hotelChain.getLogo());
    }

}
