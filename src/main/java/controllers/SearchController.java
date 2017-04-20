package controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.AvailabilityDao;
import entities.core.Availability;
import wrappers.AvailabilityWrapper;
import wrappers.SearchWrapper;

@Controller
public class SearchController {

    @Autowired
    private AvailabilityDao availibilityDao;

    public List<AvailabilityWrapper> search(SearchWrapper searchWrapper) {
        List<Availability> availabilities = availibilityDao.search(searchWrapper.getHotelName(), searchWrapper.getCity(),
                searchWrapper.getPostCode(), searchWrapper.getSlotStartDate(), searchWrapper.getSlotEndDate());
        List<AvailabilityWrapper> availabilityWrappers = new LinkedList<>();
        for (Availability availability : availabilities) {
            availabilityWrappers.add(new AvailabilityWrapper(availability));
        }
        return availabilityWrappers;
    }
}
