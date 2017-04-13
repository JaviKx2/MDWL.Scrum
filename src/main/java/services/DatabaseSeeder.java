package services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import config.ResourceNames;
import daos.users.TokenDao;
import daos.users.UserDao;
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
    
    public void createRoleUsersAndTokens(){
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
            User u = new User("hotelchainmanager@gmail.com", "hotelchainmanager", "hotelchainmanager", "hotelchainmanager surname", Permissions.HOTELCHAIN_MANAGER);
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

}
