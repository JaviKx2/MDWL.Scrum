package entities.core;

import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import entities.users.User;

public class Hotel {

    private String name;

    private String postcode;

    private String city;

    @ManyToOne
    @JoinColumn
    private User manager;

    @Lob
    private String image;

    private HotelChain hotelChain;

    public Hotel() {

    }

    public Hotel(String name, String postcode, String city, String image, User manager, HotelChain hotelChain) {
        this.name = name;
        this.postcode = postcode;
        this.city = city;
        this.manager = manager;
        this.image = image;
        this.hotelChain = hotelChain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public HotelChain getHotelChain() {
        return hotelChain;
    }

    public void setHotelChain(HotelChain hotelChain) {
        this.hotelChain = hotelChain;
    }

    @Override
    public String toString() {
        return "Hotel [name=" + name + ", postcode=" + postcode + ", city=" + city + ", manager=" + manager + ", image=" + image
                + ", hotelChain=" + hotelChain + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((hotelChain == null) ? 0 : hotelChain.hashCode());
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        result = prime * result + ((manager == null) ? 0 : manager.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
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
        Hotel other = (Hotel) obj;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (hotelChain == null) {
            if (other.hotelChain != null)
                return false;
        } else if (!hotelChain.equals(other.hotelChain))
            return false;
        if (image == null) {
            if (other.image != null)
                return false;
        } else if (!image.equals(other.image))
            return false;
        if (manager == null) {
            if (other.manager != null)
                return false;
        } else if (!manager.equals(other.manager))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (postcode == null) {
            if (other.postcode != null)
                return false;
        } else if (!postcode.equals(other.postcode))
            return false;
        return true;
    }

}
