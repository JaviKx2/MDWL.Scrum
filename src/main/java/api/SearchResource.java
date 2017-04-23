package api;

import static config.Constants.DATE_FORMAT;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import controllers.SearchController;
import wrappers.AvailabilityWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.SEARCH)
public class SearchResource {

    @Autowired
    private SearchController searchController;

    @RequestMapping(method = RequestMethod.GET)
    public List<AvailabilityWrapper> search(@RequestParam(required = false) String hotelName, @RequestParam(required = false) String city,
            @RequestParam(required = false) String postalCode,
            @RequestParam(required = false) @DateTimeFormat(pattern = DATE_FORMAT) Date slotStartDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = DATE_FORMAT) Date slotEndDate) {
        return searchController.search(hotelName, city, postalCode, slotStartDate, slotEndDate);
    }
}
