package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.RoomController;
import controllers.TokenController;
import entities.core.Room;
import entities.users.Permissions;
import wrappers.RoomWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.ROOMS)
public class RoomResource {

    @Autowired
    private RoomController roomController;
    
    @Autowired
    private TokenController tokenController;

    @RequestMapping(method = RequestMethod.GET)
    public List<Room> findAll(@RequestHeader("x-access-token") String token) {
        if (tokenController.userHasPermission(token, Permissions.HOTEL_MANAGER)){
            return roomController.findAll();
        }
        return null;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public RoomWrapper add(@RequestBody RoomWrapper roomWrapper, @RequestHeader("x-access-token") String token) {
        if (tokenController.userHasPermission(token, Permissions.HOTEL_MANAGER)){
            return roomController.add(roomWrapper);
        } else {
            return null;
        }
    }
    
    
}
