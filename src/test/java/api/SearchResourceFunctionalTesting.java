package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.joda.time.Interval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import wrappers.AvailabilityWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class SearchResourceFunctionalTesting {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    @Test
    public void testSearchAllAvailabilitiesBetween16and18() throws ParseException {
        Date slotStartDate = sdf.parse("31-08-2017 16:00");
        Date slotEndDate = sdf.parse("31-08-2017 18:00");

        List<AvailabilityWrapper> availableRooms = Arrays.asList(
                new RestBuilder<AvailabilityWrapper[]>(RestService.URL).path(Uris.SEARCH).param("slotStartDate", sdf.format(slotStartDate))
                        .param("slotEndDate", sdf.format(slotEndDate)).clazz(AvailabilityWrapper[].class).get().build());

        assertEquals(7, availableRooms.size());

        Interval searchedInterval = new Interval(slotStartDate.getTime(), slotEndDate.getTime());
        for (AvailabilityWrapper availabilityWrapper : availableRooms) {
            Interval availabilityInterval = new Interval(availabilityWrapper.getSlotStartDate().getTime(),
                    availabilityWrapper.getSlotEndDate().getTime());
            assertTrue(searchedInterval.overlaps(availabilityInterval));
        }
    }

    @Test
    public void testSearchHotel1Availabilities() throws ParseException {
        String hotelName = "hotel1";

        List<AvailabilityWrapper> availableRooms = Arrays.asList(new RestBuilder<AvailabilityWrapper[]>(RestService.URL).path(Uris.SEARCH)
                .param("hotelName", hotelName).clazz(AvailabilityWrapper[].class).get().build());

        // All from hotel1 and from hotel11, hotel12 and hotel13
        assertEquals(6, availableRooms.size());

        for (AvailabilityWrapper availabilityWrapper : availableRooms) {
            assertTrue(availabilityWrapper.getHotelName().contains(hotelName));
        }
    }

    @Test
    public void testSearchCity2Availabilities() throws ParseException {
        String city = "city2";

        List<AvailabilityWrapper> availableRooms = Arrays.asList(new RestBuilder<AvailabilityWrapper[]>(RestService.URL).path(Uris.SEARCH)
                .param("city", city).clazz(AvailabilityWrapper[].class).get().build());

        assertEquals(1, availableRooms.size());

        for (AvailabilityWrapper availabilityWrapper : availableRooms) {
            assertTrue(availabilityWrapper.getHotelCity().contains(city));
        }
    }

    @Test
    public void testSearchPostalcode3Availabilities() throws ParseException {
        String postalCode = "postcode3";

        List<AvailabilityWrapper> availableRooms = Arrays.asList(new RestBuilder<AvailabilityWrapper[]>(RestService.URL).path(Uris.SEARCH)
                .param("postalCode", postalCode).clazz(AvailabilityWrapper[].class).get().build());

        assertEquals(1, availableRooms.size());

        for (AvailabilityWrapper availabilityWrapper : availableRooms) {
            assertEquals(postalCode, availabilityWrapper.getHotelPostalCode());
        }
    }

    @Test
    public void testSearchHotel1City1AvailabilitiesBetween16and18() throws ParseException {
        String hotelName = "hotel1";
        String city = "city1";
        Date slotStartDate = sdf.parse("31-08-2017 16:00");
        Date slotEndDate = sdf.parse("31-08-2017 18:00");

        List<AvailabilityWrapper> availableRooms = Arrays.asList(new RestBuilder<AvailabilityWrapper[]>(RestService.URL).path(Uris.SEARCH)
                .param("hotelName", hotelName).param("city", city).param("slotStartDate", sdf.format(slotStartDate))
                .param("slotEndDate", sdf.format(slotEndDate)).clazz(AvailabilityWrapper[].class).get().build());

        assertEquals(4, availableRooms.size());

        Interval searchedInterval = new Interval(slotStartDate.getTime(), slotEndDate.getTime());
        for (AvailabilityWrapper availabilityWrapper : availableRooms) {
            Interval availabilityInterval = new Interval(availabilityWrapper.getSlotStartDate().getTime(),
                    availabilityWrapper.getSlotEndDate().getTime());
            assertTrue(searchedInterval.overlaps(availabilityInterval));
            assertTrue(availabilityWrapper.getHotelName().contains(hotelName));
            assertTrue(availabilityWrapper.getHotelCity().contains(city));
        }
    }
}
