package api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ReservationResourceFunctionalTesting.class, LoginResourceFunctionalTesting.class, SearchResourceFunctionalTesting.class, AvailabilityResourceFunctionalTesting.class, RoomResourceFunctionalTesting.class, HotelChainResourceFunctionalTesting.class, HotelResourceFunctionalTesting.class, UserResourceFunctionalTesting.class})
public class AllFunctionalTesting {

}
