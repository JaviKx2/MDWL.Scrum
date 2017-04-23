package controllers;

import static config.Constants.DATE_FORMAT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import wrappers.AvailabilityWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class SearchControllerIT {

    @Autowired
    private SearchController searchController;

    private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    @Test
    public void testSearchAllAvailabilitiesBetween16and18() throws ParseException {
        Date slotStartDate = sdf.parse("31-08-2017 16:00");
        Date slotEndDate = sdf.parse("31-08-2017 18:00");
        List<AvailabilityWrapper> avaliabilityWrappers = searchController.search(null, null, null, slotStartDate, slotEndDate);

        assertEquals(7, avaliabilityWrappers.size());

        Interval searchedInterval = new Interval(slotStartDate.getTime(), slotEndDate.getTime());
        for (AvailabilityWrapper availabilityWrapper : avaliabilityWrappers) {
            Interval availabilityInterval = new Interval(sdf.parse(availabilityWrapper.getSlotStartDate()).getTime(),
                    sdf.parse(availabilityWrapper.getSlotEndDate()).getTime());
            assertTrue(searchedInterval.overlaps(availabilityInterval));
        }
    }

    @Test
    public void testSearchHotel1Availabilities() throws ParseException {
        String hotelName = "hotel1";
        List<AvailabilityWrapper> avaliabilityWrappers = searchController.search(hotelName, null, null, null, null);

        // All from hotel1 and from hotel11, hotel12 and hotel13
        assertEquals(6, avaliabilityWrappers.size());

        for (AvailabilityWrapper availabilityWrapper : avaliabilityWrappers) {
            assertTrue(availabilityWrapper.getHotelName().contains(hotelName));
        }
    }

    @Test
    public void testSearchCity2Availabilities() throws ParseException {
        String cityName = "city2";
        List<AvailabilityWrapper> avaliabilityWrappers = searchController.search(null, cityName, null, null, null);

        assertEquals(1, avaliabilityWrappers.size());

        for (AvailabilityWrapper availabilityWrapper : avaliabilityWrappers) {
            assertTrue(availabilityWrapper.getHotelCity().contains(cityName));
        }
    }

    @Test
    public void testSearchPostalcode3Availabilities() throws ParseException {
        String hotelPostalcode = "postcode3";
        List<AvailabilityWrapper> avaliabilityWrappers = searchController.search(null, null, hotelPostalcode, null, null);

        assertEquals(1, avaliabilityWrappers.size());

        for (AvailabilityWrapper availabilityWrapper : avaliabilityWrappers) {
            assertTrue(availabilityWrapper.getHotelPostalCode().contains(hotelPostalcode));
        }
    }

    @Test
    public void testSearchHotel1City1AvailabilitiesBetween16and18() throws ParseException {
        String hotelName = "hotel1";
        String cityName = "city1";
        Date slotStartDate = sdf.parse("31-08-2017 16:00");
        Date slotEndDate = sdf.parse("31-08-2017 18:00");
        List<AvailabilityWrapper> avaliabilityWrappers = searchController.search(hotelName, cityName, null, slotStartDate, slotEndDate);

        assertEquals(4, avaliabilityWrappers.size());

        Interval searchedInterval = new Interval(slotStartDate.getTime(), slotEndDate.getTime());
        for (AvailabilityWrapper availabilityWrapper : avaliabilityWrappers) {
            Interval availabilityInterval = new Interval(sdf.parse(availabilityWrapper.getSlotStartDate()).getTime(),
                    sdf.parse(availabilityWrapper.getSlotEndDate()).getTime());
            assertTrue(searchedInterval.overlaps(availabilityInterval));
            assertTrue(availabilityWrapper.getHotelName().contains(hotelName));
            assertTrue(availabilityWrapper.getHotelCity().contains(cityName));
        }
    }
}
