package wrappers;

import java.util.Date;

public class SearchWrapper {
    private String hotelName;
    private String city; 
    private String postCode;
    private Date slotStartDate;
    private Date slotEndDate;
    
    public SearchWrapper() {
        super();
    }

    public SearchWrapper(String hotelName, String city, String postCode, Date slotStartDate, Date slotEndDate) {
        super();
        this.hotelName = hotelName;
        this.city = city;
        this.postCode = postCode;
        this.slotStartDate = slotStartDate;
        this.slotEndDate = slotEndDate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Date getSlotStartDate() {
        return slotStartDate;
    }

    public void setSlotStartDate(Date slotStartDate) {
        this.slotStartDate = slotStartDate;
    }

    public Date getSlotEndDate() {
        return slotEndDate;
    }

    public void setSlotEndDate(Date slotEndDate) {
        this.slotEndDate = slotEndDate;
    }
}
