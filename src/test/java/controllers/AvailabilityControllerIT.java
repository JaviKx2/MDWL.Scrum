package controllers;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import daos.core.AvailabilityDao;
import daos.core.RoomDao;
import entities.core.Availability;
import services.DatabaseSeeder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class AvailabilityControllerIT {

    @Autowired
    private AvailabilityDao availabilityDao;
    
    @Autowired
    private RoomDao roomDao;
    
    @Autowired
    private DatabaseSeeder dbSeeder;
    
    @Test
    public void testIfAvailabilityWrapperHasAllAtts(){      
        
    }
    
    @Test
    public void testIfAvailabiliesAreLinkedWithARoom() {
        List<Availability> availabilities = availabilityDao.findAll();
        for(Availability availability : availabilities){
            assertNotNull(availability.getRoom());
        }
    }

    /**
     * Leave database as it was before tests
     * @throws ParseException
     */
    @After
    public void tearDown() throws ParseException {
        dbSeeder.deleteAllExceptAdmin();
        dbSeeder.populate();
    }
}
