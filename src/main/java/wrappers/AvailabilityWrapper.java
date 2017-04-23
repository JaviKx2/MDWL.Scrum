package wrappers;

import static config.Constants.DATE_FORMAT;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import entities.core.Availability;
import entities.core.Room;
import entities.core.RoomType;

public class AvailabilityWrapper {

    private String hotelName;

    private String hotelCity;

    private String hotelPostalCode;
    
    private long roomId;

    private int roomNumber;

    private int roomCapacity;

    private RoomType roomType;

    private double roomPrice;

    private String roomServices;
    
    private String slotStartDate;

    private String slotEndDate;

    public AvailabilityWrapper() {
    }

    public AvailabilityWrapper(Availability availability) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
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
        slotStartDate = sdf.format(availability.getStartDate());
        slotEndDate = sdf.format(availability.getEndingDate());
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

    public String getSlotStartDate() {
        return slotStartDate;
    }

    public String getSlotEndDate() {
        return slotEndDate;
    }
}
