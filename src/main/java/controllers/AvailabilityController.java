package controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.AvailabilityDao;
import daos.core.RoomDao;
import entities.core.Availability;
import entities.core.Room;
import wrappers.AvailabilityCreationWrapper;

@Controller
public class AvailabilityController {
    
    private AvailabilityDao availabilityDao;
    
    private RoomDao roomDao;
    
    @Autowired
    public void setAvailabilityDao(AvailabilityDao availabilityDao){
        this.availabilityDao = availabilityDao;
    }
    
    @Autowired
    public void setRoomDao(RoomDao roomDao){
        this.roomDao = roomDao;
    }
    
    public void add(AvailabilityCreationWrapper availability){
        Room room = roomDao.findOne(availability.getRoomId());
        Date from = availability.getSlotStartDate();
        Date to = availability.getSlotEndDate();
        availabilityDao.save(new Availability(room, from, to));        
    }
    
}
