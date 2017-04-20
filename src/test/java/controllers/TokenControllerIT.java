package controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import entities.users.Permissions;
import entities.users.Token;
import entities.users.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class TokenControllerIT {

    @Autowired
    private TokenController tokenController;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;

    @Test
    public void testUserHasPermission() {
        User u = userDao.findAll().get(1);      //App manager
        Token token = tokenDao.findByUser(u);
        System.out.println(u.getEmail());
        System.out.println(token.getValue());
        assertTrue(tokenController.userHasPermission(token.getValue(), u.getPermissions()));
    }
    
    @Test
    public void testUserHasNotPermission() {
        User u = userDao.findAll().get(2);      //Basic User
        Token token = tokenDao.findByUser(u);
        assertFalse(tokenController.userHasPermission(token.getValue(), Permissions.ADMIN));
    }
}
