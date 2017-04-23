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
import daos.users.TokenDao;
import daos.users.UserDao;
import entities.core.Room;
import entities.users.User;
import services.DatabaseSeeder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class RoomResourceFunctionalTesting {
   
    private final static int HOTEL_MANAGER = 3;

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private TokenDao tokenDao;
    
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

    @After
    public void tearDown() throws ParseException {
        databaseSeeder.deleteAllExceptAdmin();
        databaseSeeder.populate();
    }
}
