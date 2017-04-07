package entities.core;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Room {

    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    private int number;

    private double price;

    @ManyToOne
    @JoinColumn
    private Hotel hotel;

    @ElementCollection
    private List<String> services;

    public Room() {
    }

    public Room(int number, RoomType type, double price, Hotel hotel, List<String> services) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.hotel = hotel;
        this.services = services;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Room [id=" + id + ", type=" + type + ", number=" + number + ", price=" + price + ", hotel=" + hotel + ", services="
                + services + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hotel == null) ? 0 : hotel.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + number;
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((services == null) ? 0 : services.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        Room other = (Room) obj;
        if (hotel == null) {
            if (other.hotel != null)
                return false;
        } else if (!hotel.equals(other.hotel))
            return false;
        if (id != other.id)
            return false;
        if (number != other.number)
            return false;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        if (services == null) {
            if (other.services != null)
                return false;
        } else if (!services.equals(other.services))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

}