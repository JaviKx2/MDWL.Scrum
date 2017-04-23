
package entities.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;
import org.joda.time.Hours;

import entities.users.Encrypting;
import entities.users.User;

@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDate;

    private int hours;

    @Column(unique = true, nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn
    private Room room;

    @ManyToOne
    @JoinColumn
    private User user;

    private int numberOfPeople;

    private double price;

    public Reservation() {

    }

    public Reservation(Date entryDate, Date departureDate, int numberOfPeople, Room room, User user) {
        this.entryDate = entryDate;
        this.departureDate = departureDate;
        this.numberOfPeople = numberOfPeople;
        setRoom(room);
        this.user = user;
        this.hours = Hours.hoursBetween(new DateTime(entryDate), new DateTime(departureDate)).getHours();
        this.price = this.hours * room.getPrice();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getCode() {
        return code;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
        this.code = new Encrypting().encryptInBase64UrlSafe(room.getId() + System.currentTimeMillis() + "");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", entryDate=" + entryDate + ", departureDate=" + departureDate + ", hours=" + hours + ", code="
                + code + ", room=" + room + ", user=" + user + ", numberOfPeople=" + numberOfPeople + ", price=" + price + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
        result = prime * result + ((entryDate == null) ? 0 : entryDate.hashCode());
        result = prime * result + hours;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + numberOfPeople;
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((room == null) ? 0 : room.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        Reservation other = (Reservation) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
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
        if (hours != other.hours)
            return false;
        if (id != other.id)
            return false;
        if (numberOfPeople != other.numberOfPeople)
            return false;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        if (room == null) {
            if (other.room != null)
                return false;
        } else if (!room.equals(other.room))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

}
