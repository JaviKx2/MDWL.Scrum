
package wrappers;

import java.util.Date;

public class ReservationPostWrapper {

    private Date entryDate;

    private Date departureDate;

    private long roomId;

    private int userId;

    private int numberOfPeople;

    public ReservationPostWrapper() {

    }

    public ReservationPostWrapper(Date entryDate, Date departureDate, long roomId, int userId, int numberOfPeople) {
        this.entryDate = entryDate;
        this.departureDate = departureDate;
        this.roomId = roomId;
        this.userId = userId;
        this.numberOfPeople = numberOfPeople;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public String toString() {
        return "ReservationPostWrapper [entryDate=" + entryDate + ", departureDate=" + departureDate + ", roomId=" + roomId + ", userId="
                + userId + ", numberOfPeople=" + numberOfPeople + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
        result = prime * result + ((entryDate == null) ? 0 : entryDate.hashCode());
        result = prime * result + numberOfPeople;
        result = prime * result + (int) (roomId ^ (roomId >>> 32));
        result = prime * result + userId;
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
        ReservationPostWrapper other = (ReservationPostWrapper) obj;
        if (departureDate == null) {
            if (other.departureDate != null)
                return false;
        } else if (!departureDate.equals(other.departureDate))
            return false;
        if (entryDate == null) {
            if (other.entryDate != null)
                return false;
        } else if (!entryDate.equals(other.entryDate))
            return false;
        if (numberOfPeople != other.numberOfPeople)
            return false;
        if (roomId != other.roomId)
            return false;
        if (userId != other.userId)
            return false;
        return true;
    }

}
