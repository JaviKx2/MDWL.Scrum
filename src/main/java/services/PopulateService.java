package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daos.core.AvailabilityDao;
import daos.core.HotelChainDao;
import daos.core.HotelDao;
import daos.core.ReservationDao;
import daos.core.RoomDao;
import daos.users.UserDao;
import entities.core.Availability;
import entities.core.Hotel;
import entities.core.HotelChain;
import entities.core.Reservation;
import entities.core.Room;
import entities.core.RoomType;
import entities.users.Permissions;
import entities.users.User;

@Service
public class PopulateService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private HotelChainDao hotelChainDao;

    @Autowired
    private HotelDao hotelDao;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private AvailabilityDao availabilityDao;

    @Autowired
    private ReservationDao reservationDao;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    public void populate() throws ParseException {
        //this.createUsers(0, 5, Permissions.HOTELCHAIN_MANAGER);
        //this.createUsers(5, 25, Permissions.HOTEL_MANAGER);
        //this.createUsers(25, 35, Permissions.BASIC);
        this.createHotelChains();
        this.createHotels();
        this.createRooms();
        this.createReservations();
        this.createAvailibilities();
    }

    public void createUsers(int initial, int size, Permissions permissions) {
        User user;
        for (int i = initial; i < size; i++) {
            user = new User("email" + i, "password" + i, "name" + i, "surname" + i, permissions);
            userDao.save(user);
        }
    }

    public void createHotelChains() {
        HotelChain hotelChain;
        for (int i = 0; i < 5; i++) {
            hotelChain = new HotelChain("hotelchain" + i, "logo" + i, userDao.findAll().get(i + 1));
            hotelChainDao.save(hotelChain);
        }
    }

    public void createHotels() {
        Hotel hotel;
        for (int i = 0; i < 20; i++) {
            hotel = new Hotel("hotel" + i, "postcode" + i, "city" + i, "image" + i, userDao.findAll().get(5 + i),
                    hotelChainDao.findAll().get(i % 5));
            hotelDao.save(hotel);
        }
    }

    public void createRooms() {
        Room room;
        List<String> services = new ArrayList<>();
        services.add("Jacuzzi");
        for (int i = 1; i < 41; i++) {
            room = new Room(i, RoomType.SINGLE, 10 + i, i, hotelDao.findAll().get(i % 20), services);
            roomDao.save(room);
        }
    }

    public void createReservations() throws ParseException {
        Reservation reservation;
        String dateString = "31-08-2017";
        for (int i = 1; i < 6; i++) {
            Date date1 = sdf.parse(dateString + " 0" + i + ":20");
            Date date2 = sdf.parse(dateString + " 1" + i + ":20");
            reservation = new Reservation("XXX" + i, date1, date2, i % 3, roomDao.findAll().get(i), userDao.findAll().get(25 + i));
            reservationDao.save(reservation);
        }
    }

    public void createAvailibilities() throws ParseException {
        Availability availability;
        String dateString = "31-08-2017";
        for (int i = 0; i < 4; i++) {
            Date date1 = sdf.parse(dateString + " 1" + i + ":20");
            Date date2 = sdf.parse(dateString + " 2" + i + ":20");
            availability = new Availability(roomDao.findAll().get(i), date1, date2);
            availabilityDao.save(availability);
        }
    }

}
