package daos;

import java.text.ParseException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import services.DatabaseSeeder;

@Service
public class DaosServiceIntegrationTests {

    @Autowired
    private DatabaseSeeder databaseSeeder;

    @PostConstruct
    public void populate() throws ParseException {
        this.databaseSeeder.populate();
    }

    public void deleteAll() {
        databaseSeeder.deleteAllExceptAdmin();
    }
}
