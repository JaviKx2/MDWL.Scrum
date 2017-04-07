package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daos.core.AvailabilityDao;
import daos.core.HotelChainDao;
import daos.core.HotelDao;
import daos.core.ReservationDao;
import daos.core.RoomDao;
import daos.users.UserDao;

@Service
public class DataService {

    @Autowired
    private DatabaseSeeder populate;

    @Autowired
    private UserDao userDao;

    @Autowired
    private HotelChainDao hotelChainDao;

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private AvailabilityDao availabilityDao;

    public void deleteAllExceptAdmin() {
        availabilityDao.deleteAll();
        reservationDao.deleteAll();
        roomDao.deleteAll();
        hotelDao.deleteAll();
        hotelChainDao.deleteAll();
        userDao.deleteAll();

        populate.createDefaultAdmin();
    }

}
