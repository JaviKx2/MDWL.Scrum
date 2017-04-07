package entities.core;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RoomTest {

    private Room room;

    @Before
    public void before() throws ParseException {
        List<String> services = new ArrayList<>();
        services.add("Jacuzzi");
        services.add("Internet");
        Hotel hotel = new Hotel("Meliá Princesa", "28008", "Madrid", "Image", null, null);
        room = new Room(101, RoomType.SINGLE, 15.5, hotel, services);
    }

    @Test
    public void testRoomCreation() {
        assertEquals(101, room.getNumber());
        assertEquals(RoomType.SINGLE, room.getType());
        assertEquals(15.5, room.getPrice(), 0.01);
        assertTrue(room.getServices().contains("Jacuzzi"));
        assertNotNull(room.getHotel());
    }

}
