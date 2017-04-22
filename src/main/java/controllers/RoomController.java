package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
