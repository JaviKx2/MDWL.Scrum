package entities.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

public class RoomTest {

    private Room room;

    @Before
    public void before() throws ParseException {
        Hotel hotel = new Hotel("Meliá Princesa", "28008", "Madrid", "Image", null, null);
        room = new Room(101, RoomType.SINGLE, 15.5, 1, hotel, "Jacuzzi, Internet");
    }

    @Test
    public void testRoomCreation() {
        assertEquals(101, room.getNumber());
        assertEquals(RoomType.SINGLE, room.getType());
        assertEquals(15.5, room.getPrice(), 0.01);
        assertEquals(1, room.getCapacity());
        assertTrue(room.getServices().contains("Jacuzzi"));
        assertNotNull(room.getHotel());
    }

}
