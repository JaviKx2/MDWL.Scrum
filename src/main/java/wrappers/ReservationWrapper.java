
package wrappers;

import java.text.SimpleDateFormat;
import java.util.Date;

import entities.core.Reservation;

public class ReservationWrapper {

    private String entryDate;

    private String departureDate;

    private int hours;

    private String code;

    private long roomId;

    private long userId;

    private int numberOfPeople;

    private double price;

    public ReservationWrapper() {

    }

    public ReservationWrapper(Date entryDate, Date departureDate, int hours, String code, long roomId, long userId, int numberOfPeople,
            double price) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy HH:mm");
        this.entryDate = sdf.format(entryDate);
        this.departureDate = sdf.format(departureDate);
        this.hours = hours;
        this.code = code;
        this.roomId = roomId;
        this.userId = userId;
        this.numberOfPeople = numberOfPeople;
        this.price = price;
    }

    public ReservationWrapper(Reservation reservation) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy HH:mm");
        this.entryDate = sdf.format(reservation.getEntryDate());
        this.departureDate = sdf.format(reservation.getDepartureDate());
        this.hours = reservation.getHours();
        this.code = reservation.getCode();
        this.roomId = reservation.getRoom().getId();
        this.userId = reservation.getUser().getId();
        this.numberOfPeople = reservation.getNumberOfPeople();
        this.price = reservation.getPrice();
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
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

    public void setCode(String code) {
        this.code = code;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
        return "ReservationWrapper [entryDate=" + entryDate + ", departureDate=" + departureDate + ", hours=" + hours + ", code=" + code
                + ", roomId=" + roomId + ", userId=" + userId + ", numberOfPeople=" + numberOfPeople + ", price=" + price + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
        result = prime * result + ((entryDate == null) ? 0 : entryDate.hashCode());
        result = prime * result + hours;
        result = prime * result + numberOfPeople;
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (int) (roomId ^ (roomId >>> 32));
        result = prime * result + (int) (userId ^ (userId >>> 32));
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
        ReservationWrapper other = (ReservationWrapper) obj;
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
        if (numberOfPeople != other.numberOfPeople)
            return false;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        if (roomId != other.roomId)
            return false;
        if (userId != other.userId)
            return false;
        return true;
    }

}
