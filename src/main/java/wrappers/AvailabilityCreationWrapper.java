package wrappers;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AvailabilityCreationWrapper {
    private static final String dateFormat = "dd-MM-yyyy HH:mm";
    
    private long roomId;
    
    @JsonFormat(pattern = dateFormat)
    private Date slotStartDate;

    @JsonFormat(pattern = dateFormat)
    private Date slotEndDate;
    
    public AvailabilityCreationWrapper(long roomId, Date slotStartDate, Date slotEndDate) {
        this.roomId = roomId;
        this.slotStartDate = slotEndDate;
        this.slotEndDate = slotEndDate;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
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
