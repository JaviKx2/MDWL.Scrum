package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.users.UserDao;
import entities.users.User;

@Controller
public class UserController {
    
    private UserDao userDao;
    
    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    
    public List<User> findAll(){      
        return userDao.findAll();  
    } 

}
