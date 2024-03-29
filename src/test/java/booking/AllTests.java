package booking;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import controllers.AllControllersIntegrationTests;
import daos.AllDaosIntegrationTests;
import entities.AllEntitiesTests;
import services.AllServicesIntegrationTests;

@RunWith(Suite.class)
@SuiteClasses({AllEntitiesTests.class, AllDaosIntegrationTests.class, AllControllersIntegrationTests.class,
        AllServicesIntegrationTests.class})
public class AllTests {

}
