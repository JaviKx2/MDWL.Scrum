package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.HotelController;
import controllers.TokenController;
import entities.core.Hotel;
import entities.users.Permissions;
import wrappers.HotelWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.HOTEL)
public class HotelResource {

    @Autowired
    private HotelController hotelController;
    
    @Autowired
    private TokenController tokenController;

    @RequestMapping(method = RequestMethod.POST)
    public HotelWrapper add(@RequestBody HotelWrapper hotelWrapper, @RequestHeader("x-access-token") String token) {
        if (tokenController.userHasPermission(token, Permissions.HOTELCHAIN_MANAGER)){
            return hotelController.add(hotelWrapper);
        } else {
            return null;
        }
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Hotel> findAll(@RequestHeader("x-access-token") String token) {
        if (tokenController.userHasPermission(token, Permissions.APP_MANAGER)){
            return hotelController.findAll();
        }
        return null;
    }
}
