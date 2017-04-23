package controllers;

import static config.Constants.DATE_FORMAT;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import services.DatabaseSeeder;
import wrappers.AvailabilityCreationWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class AvailabilityControllerIT {

    @Autowired
    private AvailabilityDao availabilityDao;

    @Autowired
    private DatabaseSeeder dbSeeder;

    @Autowired
    private AvailabilityController availabilityController;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);

    @Test
    public void testIfAvailabilityIsCreated() throws ParseException {
        Date from = dateFormatter.parse("27-09-2017 10:00");
        Date to = dateFormatter.parse("27-09-2017 12:00");
        long countPrev = availabilityDao.count();
        availabilityController.add(new AvailabilityCreationWrapper(1, from, to));
        assertEquals(countPrev + 1, availabilityDao.count());
    }

    /**
     * Leave database as it was before tests
     * 
     * @throws ParseException
     */
    @After
    public void tearDown() throws ParseException {
        dbSeeder.deleteAllExceptAdmin();
        dbSeeder.populate();
    }
}
