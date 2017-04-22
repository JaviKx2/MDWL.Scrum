package wrappers;

import java.util.Date;

public class AvailabilityCreationWrapper {
   
    private long roomId;
    
    private Date slotStartDate;

    private Date slotEndDate;
    
    public AvailabilityCreationWrapper(){
    }
    
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
    
    @Override
    public String toString() {
        return "AvailabilityCreationWrapper [roomId=" + roomId + ", slotStartDate=" + slotStartDate + ", slotEndDate=" + slotEndDate + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (roomId ^ (roomId >>> 32));
        result = prime * result + ((slotEndDate == null) ? 0 : slotEndDate.hashCode());
        result = prime * result + ((slotStartDate == null) ? 0 : slotStartDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AvailabilityCreationWrapper other = (AvailabilityCreationWrapper) obj;
        if (roomId != other.roomId)
            return false;
        if (slotEndDate == null) {
            if (other.slotEndDate != null)
                return false;
        } else if (!slotEndDate.equals(other.slotEndDate))
            return false;
        if (slotStartDate == null) {
            if (other.slotStartDate != null)
                return false;
        } else if (!slotStartDate.equals(other.slotStartDate))
            return false;
        return true;
    }
  
}
