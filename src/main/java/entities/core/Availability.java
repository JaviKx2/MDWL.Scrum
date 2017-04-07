package entities.core;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Availability {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn
    private Room room;

    private Date startDate;

    private Date endingDate;

    public Availability() {
    }

    public Availability(Room room, Date startDate, Date endingDate) {
        this.room = room;
        this.startDate = startDate;
        this.endingDate = endingDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    @Override
    public String toString() {
        return "Availability [id=" + id + ", room=" + room + ", startDate=" + startDate + ", endingDate=" + endingDate + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endingDate == null) ? 0 : endingDate.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((room == null) ? 0 : room.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
        Availability other = (Availability) obj;
        if (endingDate == null) {
            if (other.endingDate != null)
                return false;
        } else if (!endingDate.equals(other.endingDate))
            return false;
        if (id != other.id)
            return false;
        if (room == null) {
            if (other.room != null)
                return false;
        } else if (!room.equals(other.room))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        return true;
    }

}
