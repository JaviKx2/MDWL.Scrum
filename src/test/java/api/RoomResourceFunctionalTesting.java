package api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import daos.core.HotelDao;
import daos.users.TokenDao;
import daos.users.UserDao;
import entities.core.Hotel;
import entities.core.HotelChain;
import entities.core.Room;
import entities.core.RoomType;
import entities.users.User;
import services.DatabaseSeeder;
import wrappers.HotelWrapper;
import wrappers.RoomWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class RoomResourceFunctionalTesting {
   
    private final static int HOTEL_MANAGER = 3;

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private TokenDao tokenDao;
    
    @Autowired
    private HotelDao hotelDao;
    
    @Autowired
    private DatabaseSeeder databaseSeeder;
    
    @Test
    public void testFindAll() {
        User user = userDao.findAll().get(HOTEL_MANAGER);
        String tokenValue = tokenDao.findByUser(user).getValue();
        List<Room> response = Arrays.asList(
                new RestBuilder<Room[]>(RestService.URL)
                .path(Uris.ROOMS)
                .header("x-access-token", tokenValue)
                .get()
                .clazz(Room[].class)
                .build());
        assertNotNull(response);
        assertTrue(response.size() > 0);
    }
    
    @Test
    public void testAddRoom() throws ParseException {
        User user = userDao.findAll().get(2);
        String tokenValue = tokenDao.findByUser(user).getValue();
        List<Hotel> hotel = hotelDao.findAll();
        RoomWrapper roomWrapper = new RoomWrapper();
        roomWrapper.setCapacity(8);
        roomWrapper.setHotel(hotel.get(0));
        roomWrapper.setNumber(734);
        roomWrapper.setPrice(200);
        roomWrapper.setServices("Servicios");
        roomWrapper.setType(RoomType.SUITE);
        RoomWrapper room = new RestBuilder<RoomWrapper>(RestService.URL).path(Uris.ROOMS)
                .body(roomWrapper).header("x-access-token", tokenValue).clazz(RoomWrapper.class).post().build();  
        assertNotNull(hotel);
    }

    @After
    public void tearDown() throws ParseException {
        databaseSeeder.deleteAllExceptAdmin();
        databaseSeeder.populate();
    }
}
