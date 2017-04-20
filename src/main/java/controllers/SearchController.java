package controllers;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.AvailabilityDao;
import entities.core.Availability;
import wrappers.AvailabilityWrapper;

@Controller
public class SearchController {

    @Autowired
    private AvailabilityDao availibilityDao;

    public List<AvailabilityWrapper> search(String hotelName, String city, String postalCode, Date slotStartDate, Date slotEndDate) {
        List<Availability> availabilities = availibilityDao.search(hotelName, city, postalCode, slotStartDate, slotEndDate);
        List<AvailabilityWrapper> availabilityWrappers = new LinkedList<>();
        for (Availability availability : availabilities) {
            availabilityWrappers.add(new AvailabilityWrapper(availability));
        }
        return availabilityWrappers;
    }
}
