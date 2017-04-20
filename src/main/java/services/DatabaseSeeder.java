package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import config.ResourceNames;
import daos.core.AvailabilityDao;
import daos.core.HotelChainDao;
import daos.core.HotelDao;
import daos.core.ReservationDao;
import daos.core.RoomDao;
import daos.users.TokenDao;
import daos.users.UserDao;
import entities.core.Availability;
import entities.core.Hotel;
import entities.core.HotelChain;
import entities.core.Reservation;
import entities.core.Room;
import entities.core.RoomType;
import entities.users.Permissions;
import entities.users.Token;
import entities.users.User;

@Service
@Transactional
@PropertySource(ResourceNames.PROPERTIES)
public class DatabaseSeeder {

    private String adminEmail;

    private String adminPassword;

    private String adminName;

    private String adminSurname;

    @Autowired
    private Environment environment;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;

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

    @PostConstruct
    public void readAdmin() {
        adminEmail = environment.getProperty("admin.email");
        adminPassword = environment.getProperty("admin.password");
        adminName = environment.getProperty("admin.name");
        adminSurname = environment.getProperty("admin.surname");
        createDefaultAdmin();
        createRoleUsersAndTokens();
    }

    public void createDefaultAdmin() {
        List<User> admins = userDao.findByPermissions(Permissions.ADMIN);
        if (admins.isEmpty()) {
            User admin = new User(adminEmail, adminPassword, adminName, adminSurname, Permissions.ADMIN);
            userDao.save(admin);
            Token token = new Token(admin);
            tokenDao.save(token);
        }
    }

    public void createRoleUsersAndTokens() {
        List<User> appManagers = userDao.findByPermissions(Permissions.APP_MANAGER);
        if (appManagers.isEmpty()) {
            User u = new User("appmanager@gmail.com", "appmanager", "appmanager", "appmanager surname", Permissions.APP_MANAGER);
            userDao.save(u);
            Token token = new Token(u);
            tokenDao.save(token);
        }
        List<User> basicUsers = userDao.findByPermissions(Permissions.BASIC);
        if (basicUsers.isEmpty()) {
            User u = new User("basic@gmail.com", "basic", "basic", "basic surname", Permissions.BASIC);
            userDao.save(u);
            Token token = new Token(u);
            tokenDao.save(token);
        }
        List<User> hotelManagers = userDao.findByPermissions(Permissions.HOTEL_MANAGER);
        if (hotelManagers.isEmpty()) {
            User u = new User("hotelmanager@gmail.com", "hotelmanager", "hotelmanager", "hotelmanager surname", Permissions.HOTEL_MANAGER);
            userDao.save(u);
            Token token = new Token(u);
            tokenDao.save(token);
        }
        List<User> hotelChainManagers = userDao.findByPermissions(Permissions.HOTELCHAIN_MANAGER);
        if (hotelChainManagers.isEmpty()) {
            User u = new User("hotelchainmanager@gmail.com", "hotelchainmanager", "hotelchainmanager", "hotelchainmanager surname",
                    Permissions.HOTELCHAIN_MANAGER);
            userDao.save(u);
            Token token = new Token(u);
            tokenDao.save(token);
        }
        List<User> receptionists = userDao.findByPermissions(Permissions.RECEPTIONIST);
        if (receptionists.isEmpty()) {
            User u = new User("receptionist@gmail.com", "receptionist", "receptionist", "receptionist surname", Permissions.RECEPTIONIST);
            userDao.save(u);
            Token token = new Token(u);
            tokenDao.save(token);
        }
    }

    public void populate() throws ParseException {
        createDefaultAdmin();
        createRoleUsersAndTokens();
        this.createHotelChains();
        this.createHotels();
        this.createRooms();
        this.createReservations();
        this.createAvailibilities();
    }

    public void createHotelChains() {
        HotelChain hotelChain;
        for (int i = 0; i < 5; i++) {
            hotelChain = new HotelChain("hotelchain" + i, "logo" + i, userDao.findByPermissions(Permissions.HOTELCHAIN_MANAGER).get(0));
            hotelChainDao.save(hotelChain);
        }
    }

    public void createHotels() {
        Hotel hotel;
        for (int i = 0; i < 20; i++) {
            hotel = new Hotel("hotel" + i, "postcode" + i, "city" + i, "image" + i, userDao.findByPermissions(Permissions.HOTEL_MANAGER).get(0),
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
            reservation = new Reservation("XXX" + i, date1, date2, i % 3, roomDao.findAll().get(i), userDao.findByPermissions(Permissions.BASIC).get(0));
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
        
        availability = new Availability(roomDao.findByHotelName("hotel12").get(0), sdf.parse(dateString + " 04:00"), sdf.parse(dateString + " 20:00"));
        availabilityDao.save(availability);
        availability = new Availability(roomDao.findByHotelName("hotel11").get(0), sdf.parse(dateString + " 17:00"), sdf.parse(dateString + " 21:00"));
        availabilityDao.save(availability);
        availability = new Availability(roomDao.findByHotelName("hotel11").get(0), sdf.parse(dateString + " 15:00"), sdf.parse(dateString + " 16:00"));
        availabilityDao.save(availability);
        availability = new Availability(roomDao.findByHotelName("hotel13").get(0), sdf.parse(dateString + " 17:00"), sdf.parse(dateString + " 18:00"));
        availabilityDao.save(availability);
        availability = new Availability(roomDao.findByHotelName("hotel13").get(0), sdf.parse(dateString + " 21:00"), sdf.parse(dateString + " 23:00"));
        availabilityDao.save(availability);
    }
    
    public void deleteAllExceptAdmin() {
        availabilityDao.deleteAll();
        reservationDao.deleteAll();
        roomDao.deleteAll();
        hotelDao.deleteAll();
        hotelChainDao.deleteAll();
        tokenDao.deleteAll();
        userDao.deleteAll();

        createDefaultAdmin();
    }

}
