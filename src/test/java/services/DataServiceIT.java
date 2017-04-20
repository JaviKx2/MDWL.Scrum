package services;

import java.text.ParseException;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class DataServiceIT {

    @Autowired
    DatabaseSeeder databaseSeeder;

    @Test
    public void testDeleteAllExceptAdmin() {
        databaseSeeder.deleteAllExceptAdmin();
    }
    
    /**
     * Leave database as it was before tests
     * @throws ParseException
     */
    @After
    public void tearDown() throws ParseException {
        databaseSeeder.populate();
    }
}
