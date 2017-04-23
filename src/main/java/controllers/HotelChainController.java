package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.impl.AddDelegateTransformer;
import org.springframework.stereotype.Controller;

import wrappers.RoomWrapper;
import daos.core.HotelChainDao;
import daos.core.RoomDao;
import daos.users.UserDao;
import entities.core.HotelChain;
import entities.core.Room;
import entities.users.User;

@Controller
public class HotelChainController {
    
    private HotelChainDao hotelChainDao;
    
    @Autowired
    public void setUserDao(HotelChainDao hotelChainDao){
        this.hotelChainDao = hotelChainDao;
    }
    
    public List<HotelChain> findAll(){      
        return hotelChainDao.findAll();  
    } 

}
