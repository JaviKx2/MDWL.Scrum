package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.HotelChainDao;
import daos.core.HotelDao;
import entities.core.Hotel;
import wrappers.HotelWrapper;

@Controller
public class HotelController {
    
    private HotelDao hotelDao;
    
    private HotelChainDao hotelChainDao;
    
    @Autowired
    public void setHotelDao(HotelDao hotelDao){
        this.hotelDao = hotelDao;
    }
    
    @Autowired
    public void setHotelChainDao(HotelChainDao hotelChainDao){
        this.hotelChainDao = hotelChainDao;
    }
    
    public HotelWrapper add (HotelWrapper hotelWrapper){
        Hotel hotel = new Hotel();
        hotel.setName(hotelWrapper.getName());
        hotel.setCity(hotelWrapper.getCity());
        System.out.println("HOTEL CHAIN" + hotel.getHotelChain());
        hotel.setHotelChain(hotelChainDao.findOne(hotelWrapper.getHotelChainId()));
        hotel.setImage(hotelWrapper.getImage());
        hotel.setManager(hotelWrapper.getManager());
        hotel.setPostcode(hotelWrapper.getPostcode());
        Hotel newHotel = hotelDao.save(hotel);
        hotelWrapper.setId(newHotel.getId());
        return hotelWrapper;
    }
    
    public List<Hotel> findAll(){      
        return hotelDao.findAll();  
    } 
}
