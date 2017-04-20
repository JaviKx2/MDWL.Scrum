package api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import wrappers.LoginDataWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class LoginResourceFunctionalTesting {

    @Test
    public void testLoginCorrecto() {
        LoginDataWrapper data = new LoginDataWrapper("basic@gmail.com", "basic");
        String result = new RestBuilder<String>(RestService.URL).path(Uris.LOGIN).body(data).clazz(String.class).post().build();
        assertNotNull(result);
    }

    @Test
    public void testLoginIncorrecto() {
        LoginDataWrapper data = new LoginDataWrapper("DATOS ERRONEOS", "DATO ERRONEO");
        String result = new RestBuilder<String>(RestService.URL).path(Uris.LOGIN).body(data).clazz(String.class).post().build();
        assertNull(result);
    }

}
