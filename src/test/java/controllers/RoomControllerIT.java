package controllers;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import services.DatabaseSeeder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class RoomControllerIT {

    @Autowired
    private DatabaseSeeder dbSeeder;
    
    @Autowired
    private RoomController roomController;
    
    @Test
    public void testFindAll() {   
        assertNotNull(roomController.findAll());        
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
