package daos.users;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.users.Permissions;
import entities.users.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class UserDaoIT {

    @Autowired
    private UserDao userDao;

    @Test
    public void testCountUsers() {
        assertEquals(6, userDao.count());
    }

    @Test
    public void testOneUser() {
        User user = userDao.findAll().get(1);
        assertEquals("appmanager@gmail.com", user.getEmail());
        assertTrue(user.checkPassword("appmanager"));
        assertEquals("appmanager", user.getName());
        assertEquals("appmanager surname", user.getSurname());
        assertEquals(Permissions.APP_MANAGER, user.getPermissions());
    }

}
