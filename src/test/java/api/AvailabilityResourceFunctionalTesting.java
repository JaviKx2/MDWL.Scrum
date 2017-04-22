package api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import daos.core.RoomDao;
import daos.users.TokenDao;
import daos.users.UserDao;
import entities.users.User;
import services.DatabaseSeeder;
import wrappers.AvailabilityCreationWrapper;
import wrappers.ReservationWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class AvailabilityResourceFunctionalTesting {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoomDao roomDao;
    
    @Autowired
    private TokenDao tokenDao;
    
    @Autowired
    private DatabaseSeeder databaseSeeder;
    

    @Test
    public void testAddAvailability() throws ParseException{
        Date from = sdf.parse("31-08-2017 16:00");
        Date to = sdf.parse("31-08-2017 19:00");
        User user = userDao.findAll().get(2);
        String tokenValue = tokenDao.findByUser(user).getValue();
        new RestBuilder<Object>(RestService.URL).path(Uris.AVAILABILITIES).body(new AvailabilityCreationWrapper(1, from, to))
        .header("x-access-token", tokenValue).post().clazz(Object.class).build();
    }

}
