package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
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
import daos.core.HotelChainDao;
import daos.users.TokenDao;
import daos.users.UserDao;
import entities.core.Hotel;
import entities.core.HotelChain;
import entities.core.Room;
import entities.users.User;
import services.DatabaseSeeder;
import wrappers.HotelWrapper;
import wrappers.ReservationPostWrapper;
import wrappers.ReservationWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class HotelResourceFunctionalTesting {
   
    private final static int APP_MANAGER = 4;

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private TokenDao tokenDao;
    
    @Autowired
    private HotelChainDao hotelChainDao;
    
    @Autowired
    private DatabaseSeeder databaseSeeder;
    
    @Test
    public void testFindAll() {
        User user = userDao.findAll().get(APP_MANAGER);
        String tokenValue = tokenDao.findByUser(user).getValue();
        List<Hotel> response = Arrays.asList(
                new RestBuilder<Hotel[]>(RestService.URL)
                .path(Uris.HOTEL)
                .header("x-access-token", tokenValue)
                .get()
                .clazz(Hotel[].class)
                .build());
        assertNotNull(response);
        assertTrue(response.size() > 0);
    }
    
    @Test
    public void testAddHotel() throws ParseException {
        User user = userDao.findAll().get(2);
        String tokenValue = tokenDao.findByUser(user).getValue();
        List<HotelChain> hotelChain = hotelChainDao.findAll();
        HotelWrapper hotelWrapper = new HotelWrapper(10, "Nombre Hotel", "Codigo Postal" , "City", user, "Imagen", hotelChain.get(0));
        HotelWrapper hotel = new RestBuilder<HotelWrapper>(RestService.URL).path(Uris.HOTEL)
                .body(hotelWrapper).header("x-access-token", tokenValue).clazz(HotelWrapper.class).post().build();  
        assertNotNull(hotel);
    }

    @After
    public void tearDown() throws ParseException {
        databaseSeeder.deleteAllExceptAdmin();
        databaseSeeder.populate();
    }
}
