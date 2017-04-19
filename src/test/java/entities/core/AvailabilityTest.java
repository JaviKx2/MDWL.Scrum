package entities.core;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class AvailabilityTest {

    private Availability availability;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @Before
    public void before() throws ParseException {
        Room room = new Room(101, RoomType.SINGLE, 15.5, 1, null, null);
        Date entryDate = sdf.parse("31-08-2017 10:20");
        Date departureDate = sdf.parse("04-09-2017 20:40");
        availability = new Availability(room, entryDate, departureDate);
    }

    @Test
    public void testAvailabilityCreation() throws ParseException {
        assertNotNull(availability.getRoom());
        assertEquals(sdf.parse("31-08-2017 10:20"), availability.getStartDate());
        assertEquals(sdf.parse("04-09-2017 20:40"), availability.getEndingDate());
    }

}
