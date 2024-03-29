package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.TokenController;
import controllers.UserController;
import entities.users.Permissions;
import entities.users.User;

@RestController
@RequestMapping(Uris.VERSION + Uris.USER)
public class UserResource {

    @Autowired
    private UserController userController;
    
    @Autowired
    private TokenController tokenController;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll(@RequestHeader("x-access-token") String token) {
        if (tokenController.userHasPermission(token, Permissions.HOTEL_MANAGER)){
            return userController.findAll();
        }
        return null;
    }
}
