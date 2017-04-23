package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.impl.AddDelegateTransformer;
import org.springframework.stereotype.Controller;

import wrappers.RoomWrapper;
import daos.core.RoomDao;
import entities.core.Room;

@Controller
public class RoomController {
    private RoomDao roomDao;
    @Autowired
    public void setRoomDao(RoomDao roomDao){
        this.roomDao = roomDao;
    }
    
    public List<Room> findAll(){      
        return roomDao.findAll();  
    } 
    
    public RoomWrapper add (RoomWrapper roomWrapper){
        Room room = new Room();
        room.setCapacity(roomWrapper.getCapacity());
        room.setHotel(roomWrapper.getHotel());
        room.setNumber(roomWrapper.getNumber());
        room.setPrice(roomWrapper.getPrice());
        room.setServices(roomWrapper.getServices());
        room.setType(roomWrapper.getType());
        Room newRoom = roomDao.save(room);
        roomWrapper.setId(newRoom.getId());
        return roomWrapper;
    }
}
