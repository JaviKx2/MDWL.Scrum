package api;

import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import daos.core.RoomDao;
import daos.users.TokenDao;
import daos.users.UserDao;
import services.DatabaseSeeder;

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
    

    @Test(expected = HttpClientErrorException.class)
    public void testWithNoHeader(){
        
    }

}
