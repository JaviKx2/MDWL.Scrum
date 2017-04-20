package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.LoginController;
import wrappers.LoginDataWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.LOGIN)
public class LoginResource {

    @Autowired
    private LoginController loginController;

    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestBody LoginDataWrapper loginDataWrapper) {
        return loginController.login(loginDataWrapper);
    }

}
