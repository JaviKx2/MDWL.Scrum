package entities.core;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import entities.users.Permissions;
import entities.users.User;

public class UserTest {

    private User user;

    @Before
    public void before() throws ParseException {
        user = new User("mdw@upm.es", "12341234", "Rodrigo", "García", Permissions.BASIC);
    }

    @Test
    public void testUserCreation() {
        assertEquals("mdw@upm.es", user.getEmail());
        assertEquals("12341234", user.getPassword());
        assertEquals("Rodrigo", user.getName());
        assertEquals("García", user.getSurname());
        assertEquals(Permissions.BASIC, user.getPermissions());
    }

}
