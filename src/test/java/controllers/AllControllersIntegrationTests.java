package controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ReservationControllerIT.class, AdminControllerIT.class, TokenControllerIT.class, SearchControllerIT.class})
public class AllControllersIntegrationTests {

}
