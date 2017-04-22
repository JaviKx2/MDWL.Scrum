package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.AvailabilityController;
import controllers.TokenController;
import entities.users.Permissions;
import wrappers.AvailabilityCreationWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.AVAILABILITIES)
public class AvailabilityResource {

    @Autowired
    private AvailabilityController availabilityController;
    
    @Autowired
    private TokenController tokenController;

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody AvailabilityCreationWrapper availabilityWrapper, @RequestHeader("x-access-token") String token) {
        if (tokenController.userHasPermission(token, Permissions.HOTEL_MANAGER)){
            availabilityController.add(availabilityWrapper);
        }
    }
}
