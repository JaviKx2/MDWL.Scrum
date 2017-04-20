package controllers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.DatabaseSeeder;

@Controller
public class AdminController {

    @Autowired
    private DatabaseSeeder databaseSeeder;

    public void deleteAllExceptAdmin() {
        databaseSeeder.deleteAllExceptAdmin();
    }

    public void populate() throws ParseException {
        databaseSeeder.populate();
    }
}
