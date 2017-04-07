package services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import config.ResourceNames;
import daos.users.UserDao;
import entities.users.Permissions;
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

    @PostConstruct
    public void readAdmin() {
        adminEmail = environment.getProperty("admin.email");
        adminPassword = environment.getProperty("admin.password");
        adminName = environment.getProperty("admin.name");
        adminSurname = environment.getProperty("admin.surname");
        createDefaultAdmin();
    }

    public void createDefaultAdmin() {
        List<User> admins = userDao.findByPermissions(Permissions.ADMIN);
        if (admins.isEmpty()) {
            User admin = new User(adminEmail, adminPassword, adminName, adminSurname, Permissions.ADMIN);
            userDao.save(admin);
        }
    }

}
