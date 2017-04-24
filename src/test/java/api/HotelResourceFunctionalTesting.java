package api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import daos.users.TokenDao;
import daos.users.UserDao;
import entities.core.Hotel;
import entities.users.User;
import services.DatabaseSeeder;
import wrappers.HotelWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class HotelResourceFunctionalTesting {
   
    private final static int APP_MANAGER = 1;
    private final static int HOTELCHAIN_MANAGER = 4;

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private TokenDao tokenDao;
    
    @Autowired
    private DatabaseSeeder databaseSeeder;
    
    @Test
    public void testFindAll() {
        User user = userDao.findAll().get(APP_MANAGER);
        String tokenValue = tokenDao.findByUser(user).getValue();
        System.out.println(user);
        System.out.println(tokenValue);
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
        User user = userDao.findAll().get(HOTELCHAIN_MANAGER);
        String tokenValue = tokenDao.findByUser(user).getValue();
        HotelWrapper hotelWrapper = new HotelWrapper(50, "Nombre Hotel", "Codigo Postal" , "City", user, "Imagen", 1);
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
