package wrappers;

import entities.core.Hotel;
import entities.core.RoomType;

public class RoomWrapper {
    private long id;

    private RoomType type;

    private int number;

    private double price;

    private int capacity;

    private Hotel hotel;

    private String services;

    public RoomWrapper(){
        
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "RoomWrapper [id=" + id + ", type=" + type + ", number=" + number + ", price=" + price + ", capacity=" + capacity
                + ", hotel=" + hotel + ", services=" + services + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + capacity;
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
        RoomWrapper other = (RoomWrapper) obj;
        if (capacity != other.capacity)
            return false;
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
