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
        assertEquals(36, userDao.count());
    }

    @Test
    public void testOneUser() {
        User user = userDao.findAll().get(1);
        assertEquals("email0", user.getEmail());
        assertEquals("password0", user.getPassword());
        assertEquals("name0", user.getName());
        assertEquals("surname0", user.getSurname());
        assertEquals(Permissions.HOTELCHAIN_MANAGER, user.getPermissions());
    }

}
