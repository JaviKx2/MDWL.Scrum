package entities.core;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import entities.users.Permissions;
import entities.users.User;

public class HotelChainTest {

    private HotelChain hotelChain;

    @Before
    public void before() throws ParseException {
        hotelChain = new HotelChain("Hoteles Meliá", "logo",
                new User("mdw@upm.es", "12341234", "Rodrigo", "García", Permissions.HOTELCHAIN_MANAGER));
    }

    @Test
    public void testRoomCreation() {
        assertEquals("Hoteles Meliá", hotelChain.getName());
        assertEquals("logo", hotelChain.getLogo());
        assertNotNull(hotelChain.getManager());
    }

}
