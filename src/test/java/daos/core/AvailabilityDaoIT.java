package daos.core;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.Interval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.Availability;
import entities.core.Room;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class AvailabilityDaoIT {

    @Autowired
    private AvailabilityDao availabilityDao;

    @Autowired
    private RoomDao roomDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @Test
    public void testCountAvailability() {
        assertEquals(9, availabilityDao.count());
    }

    @Test
    public void testOneAvailability() throws ParseException {
        Availability availability = availabilityDao.findAll().get(0);
        assertEquals(sdf.parse("31-08-2017 10:20"), availability.getStartDate());
        assertEquals(sdf.parse("31-08-2017 20:20"), availability.getEndingDate());
        assertEquals(1, availability.getRoom().getNumber());
    }

    @Test
    public void testFindIfRoomIsAvailable() throws ParseException {
        Room room = roomDao.findAll().get(0);
        assertEquals(1,
                availabilityDao.findIfRoomIsAvailable(room.getId(), sdf.parse("31-08-2017 11:20"), sdf.parse("31-08-2017 19:20")).size());
        assertTrue(availabilityDao.findIfRoomIsAvailable(room.getId(), sdf.parse("31-08-2017 11:20"), sdf.parse("31-08-2017 23:20"))
                .isEmpty());
    }

    @Test
    public void testSearchAllAvailabilitiesBetween16and18() throws ParseException {
        Date slotStartDate = sdf.parse("31-08-2017 16:00");
        Date slotEndDate = sdf.parse("31-08-2017 18:00");
        List<Availability> avaliabilities = availabilityDao.search(null, null, null, slotStartDate, slotEndDate);
        
        assertEquals(7, avaliabilities.size());
        
        Interval searchedInterval = new Interval(slotStartDate.getTime(), slotEndDate.getTime());
        for (Availability availability : avaliabilities) {
            Interval availabilityInterval = new Interval(availability.getStartDate().getTime(), availability.getEndingDate().getTime());
            assertTrue(searchedInterval.overlaps(availabilityInterval));
        }
    }
    
    @Test
    public void testSearchHotel12Availabilities() throws ParseException {
        String hotelName = "hotel12";
        List<Availability> avaliabilities = availabilityDao.search(hotelName, null, null, null, null);
        assertEquals(1, avaliabilities.size());
        
        for (Availability availability : avaliabilities) {
            assertTrue(availability.getRoom().getHotel().getName().equals(hotelName));
        }
    }
    
    @Test
    public void testSearchCity13Availabilities() throws ParseException {
        String cityName = "city13";
        List<Availability> avaliabilities = availabilityDao.search(null, cityName, null, null, null);
        
        assertEquals(2, avaliabilities.size());
        
        for (Availability availability : avaliabilities) {
            assertTrue(availability.getRoom().getHotel().getCity().contains(cityName));
        }
    }
    
    @Test
    public void testSearchPostcode11Availabilities() throws ParseException {
        String hotelPostcode = "postcode11";
        List<Availability> avaliabilities = availabilityDao.search(null, null, hotelPostcode, null, null);
        
        assertEquals(2, avaliabilities.size());
        
        for (Availability availability : avaliabilities) {
            assertTrue(availability.getRoom().getHotel().getPostcode().contains(hotelPostcode));
        }
    }
    
    @Test
    public void testSearchHotel11PostCode11City11AvailabilitiesBetween16and18() throws ParseException {
        String hotelName = "hotel11";
        String cityName = "city11";
        String hotelPostcode = "postcode11";
        Date slotStartDate = sdf.parse("31-08-2017 16:00");
        Date slotEndDate = sdf.parse("31-08-2017 18:00");
        List<Availability> avaliabilities = availabilityDao.search(hotelName, cityName, hotelPostcode, slotStartDate, slotEndDate);
        
        assertEquals(1, avaliabilities.size());
        
        Interval searchedInterval = new Interval(slotStartDate.getTime(), slotEndDate.getTime());
        for (Availability availability : avaliabilities) {
            Interval availabilityInterval = new Interval(availability.getStartDate().getTime(), availability.getEndingDate().getTime());
            assertTrue(searchedInterval.overlaps(availabilityInterval));
            assertTrue(availability.getRoom().getHotel().getName().contains(hotelName));
            assertTrue(availability.getRoom().getHotel().getCity().contains(cityName));
            assertTrue(availability.getRoom().getHotel().getPostcode().contains(hotelPostcode));
        }
    }
}
