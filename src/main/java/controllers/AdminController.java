package controllers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.DataService;

@Controller
public class AdminController {

    @Autowired
    private DataService dataService;

    public void deleteAllExceptAdmin() {
        dataService.deleteAllExceptAdmin();
    }

    public void populate() throws ParseException {
        dataService.populate();
    }
}
