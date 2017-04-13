package api;

import static org.junit.Assert.*;

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
import daos.users.UserDao;
import entities.core.Room;
import entities.users.User;
import wrappers.LoginDataWrapper;
import wrappers.ReservationPostWrapper;
import wrappers.ReservationWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class LoginResourceFunctionalTesting {

    @Autowired
    private UserDao userDao;
    
    @Test
    public void testLoginCorrecto(){
        LoginDataWrapper data = new LoginDataWrapper("basic@gmail.com", "basic");
        String result = new RestBuilder<String>(RestService.URL).path(Uris.LOGIN)
                .body(data).clazz(String.class).post().build();
        assertNotNull(result);        
    }
    
    @Test
    public void testLoginIncorrecto(){
        LoginDataWrapper data = new LoginDataWrapper("DATOS ERRONEOS", "DATO ERRONEO");
        String result = new RestBuilder<String>(RestService.URL).path(Uris.LOGIN)
                .body(data).clazz(String.class).post().build();
        assertNull(result);
    }

}
