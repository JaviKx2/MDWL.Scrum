package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.impl.AddDelegateTransformer;
import org.springframework.stereotype.Controller;

import wrappers.RoomWrapper;
import daos.core.RoomDao;
import daos.users.UserDao;
import entities.core.Room;
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
