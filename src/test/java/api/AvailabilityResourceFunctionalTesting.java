package api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import entities.users.User;
import services.DatabaseSeeder;
import wrappers.AvailabilityCreationWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class AvailabilityResourceFunctionalTesting {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    
    private final static int HOTEL_MANAGER = 3;

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private TokenDao tokenDao;
    
    @Autowired
    private DatabaseSeeder databaseSeeder;

    @Test
    public void testAddAvailability() throws ParseException{
        Date from = sdf.parse("31-07-2017 16:00");
        Date to = sdf.parse("31-07-2017 19:00");
        long roomId = 1;
        User user = userDao.findAll().get(HOTEL_MANAGER);
        String tokenValue = tokenDao.findByUser(user).getValue();
        new RestBuilder<Object>(RestService.URL).path(Uris.AVAILABILITIES).body(new AvailabilityCreationWrapper(roomId, from, to))
        .header("x-access-token", tokenValue).post().clazz(Object.class).build();
    }

    @After
    public void tearDown() throws ParseException {
        databaseSeeder.deleteAllExceptAdmin();
        databaseSeeder.populate();
    }
}
