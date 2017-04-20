package daos;

import java.text.ParseException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import services.DataService;

@Service
public class DaosServiceIntegrationTests {

    @Autowired
    private DataService dataService;

    @PostConstruct
    public void populate() throws ParseException {
        this.dataService.populate();
    }

    public void deleteAll() {
        dataService.deleteAllExceptAdmin();
    }
}
