package api;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.AdminController;

@RestController
@RequestMapping(Uris.VERSION + Uris.ADMIN)
public class AdminResource {

    @Autowired
    private AdminController adminController;

    @RequestMapping(method = RequestMethod.GET)
    public String version(String param) {
        return "{\"version\":\"" + Uris.VERSION + "\"}";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllExceptAdmin() {
        adminController.deleteAllExceptAdmin();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void populate() throws ParseException {
        adminController.populate();
    }

}
