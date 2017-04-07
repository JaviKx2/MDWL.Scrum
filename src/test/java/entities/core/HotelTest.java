package entities.core;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import entities.users.Permissions;
import entities.users.User;

public class HotelTest {

    private Hotel hotel;

    @Before
    public void before() throws ParseException {
        HotelChain hotelChain = new HotelChain("Hoteles Meliá", "logo",
                new User("mdw@upm.es", "12341234", "Rodrigo", "García", Permissions.HOTELCHAIN_MANAGER));
        User manager = new User("mdw2@upm.es", "43214321", "Carlos", "Méndez", Permissions.HOTEL_MANAGER);
        hotel = new Hotel("Meliá Princesa", "28008", "Madrid", "Image", manager, hotelChain);
    }

    @Test
    public void testRoomCreation() {
        assertEquals("Meliá Princesa", hotel.getName());
        assertEquals("28008", hotel.getPostcode());
        assertEquals("Madrid", hotel.getCity());
        assertEquals("Image", hotel.getImage());
        assertNotNull(hotel.getManager());
        assertNotNull(hotel.getHotelChain());
    }

}
