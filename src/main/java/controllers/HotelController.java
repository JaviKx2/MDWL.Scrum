package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.impl.AddDelegateTransformer;
import org.springframework.stereotype.Controller;

import wrappers.HotelWrapper;
import wrappers.RoomWrapper;
import daos.core.HotelDao;
import daos.core.RoomDao;
import entities.core.Hotel;
import entities.core.HotelChain;
import entities.core.Room;

@Controller
public class HotelController {
    
    private HotelDao hotelDao;
    
    @Autowired
    public void setHotelDao(HotelDao hotelDao){
        this.hotelDao = hotelDao;
    }
    
    public HotelWrapper add (HotelWrapper hotelWrapper){
        Hotel hotel = new Hotel();
        hotel.setName(hotelWrapper.getName());
        hotel.setCity(hotelWrapper.getCity());
        hotel.setHotelChain(hotelWrapper.getHotelChain());
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
