package wrappers;

import entities.users.User;

public class HotelWrapper {
    private long id;

    private String name;

    private String postcode;

    private String city;

    private User manager;

    private String image;

    private long hotelChainId;

    public HotelWrapper() {

    }

    public HotelWrapper(long id, String name, String postcode, String city, User manager, String image, long hotelChainId) {
        this.id = id;
        this.name = name;
        this.postcode = postcode;
        this.city = city;
        this.manager = manager;
        this.image = image;
        this.hotelChainId = hotelChainId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getHotelChain() {
        return hotelChainId;
    }

    public void setHotelChain(long hotelChain) {
        this.hotelChainId = hotelChain;
    }

    @Override
    public String toString() {
        return "HotelWrapper [id=" + id + ", name=" + name + ", postcode=" + postcode + ", city=" + city + ", manager=" + manager
                + ", image=" + image + ", hotelChain=" + hotelChainId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + (int) (hotelChainId ^ (hotelChainId >>> 32));
        result = prime * result + (int) (id ^ (id >>> 32));
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
        HotelWrapper other = (HotelWrapper) obj;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (hotelChainId != other.hotelChainId)
            return false;
        if (id != other.id)
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
