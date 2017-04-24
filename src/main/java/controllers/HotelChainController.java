package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.HotelChainDao;
import entities.core.HotelChain;

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
