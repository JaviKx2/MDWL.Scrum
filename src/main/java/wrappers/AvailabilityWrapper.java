package wrappers;

import java.util.Date;

import entities.core.Availability;
import entities.core.Room;
import entities.core.RoomType;

public class AvailabilityWrapper {
    
    private String hotelName;
    
    private String hotelCity;
    
    private String hotelPostalCode;
    
    private int roomCapacity;
    
    private RoomType roomType;
    
    private double roomPrice;

    private Date slotStartDate;

    private Date slotEndDate;
    
    public AvailabilityWrapper() {
    }
    
    public AvailabilityWrapper(Availability availability) {
        Room room = availability.getRoom();
        hotelName = room.getHotel().getName();
        hotelCity = room.getHotel().getCity();
        hotelPostalCode = room.getHotel().getPostcode();
        roomCapacity = room.getCapacity();
        roomType = room.getType();
        roomPrice = room.getPrice();
        slotStartDate = availability.getStartDate();
        slotEndDate = availability.getEndingDate();
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public String getHotelPostalCode() {
        return hotelPostalCode;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public Date getStartDate() {
        return slotStartDate;
    }

    public Date getEndingDate() {
        return slotEndDate;
    }
}
