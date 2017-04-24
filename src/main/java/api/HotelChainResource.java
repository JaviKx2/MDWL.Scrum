package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.HotelChainController;
import controllers.TokenController;
import entities.core.HotelChain;
import entities.users.Permissions;

@RestController
@RequestMapping(Uris.VERSION + Uris.HOTEL_CHAIN)
public class HotelChainResource {

    @Autowired
    private HotelChainController hotelChainController;
    
    @Autowired
    private TokenController tokenController;

    @RequestMapping(method = RequestMethod.GET)
    public List<HotelChain> findAll(@RequestHeader("x-access-token") String token) {
        if (tokenController.userHasPermission(token, Permissions.APP_MANAGER)){
            return hotelChainController.findAll();
        }
        return null;
    }
}
