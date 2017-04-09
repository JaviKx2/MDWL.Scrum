package daos.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({AvailabilityDaoIT.class, HotelChainDaoIT.class, HotelDaoIT.class, ReservationDaoIT.class, RoomDaoIT.class})
public class AllDaosCoreIntegrationTests {

}
