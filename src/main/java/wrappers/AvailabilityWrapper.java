package wrappers;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import entities.core.Availability;
import entities.core.Room;
import entities.core.RoomType;

public class AvailabilityWrapper {
    
    private static final String dateFormat = "dd-MM-yyyy HH:mm";

    private String hotelName;

    private String hotelCity;

    private String hotelPostalCode;
    
    private long roomId;

    private int roomNumber;

    private int roomCapacity;

    private RoomType roomType;

    private double roomPrice;

    private String roomServices;

    @JsonFormat(pattern = dateFormat)
    private Date slotStartDate;

    @JsonFormat(pattern = dateFormat)
    private Date slotEndDate;

    public AvailabilityWrapper() {
    }

    public AvailabilityWrapper(Availability availability) {
        Room room = availability.getRoom();
        hotelName = room.getHotel().getName();
        hotelCity = room.getHotel().getCity();
        hotelPostalCode = room.getHotel().getPostcode();
        roomId = room.getId();
        roomNumber = room.getNumber();
        roomCapacity = room.getCapacity();
        roomType = room.getType();
        roomPrice = room.getPrice();
        roomServices = room.getServices();
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

    public long getRoomId() {
        return roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
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

    public String getRoomServices() {
        return roomServices;
    }

    public Date getSlotStartDate() {
        return slotStartDate;
    }

    public Date getSlotEndDate() {
        return slotEndDate;
    }
}
