package daos.core;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entities.core.Room;

public interface RoomDao extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r.hotel.name = :hotelName")
    List<Room> findByHotelName(@Param("hotelName") String hotelName);
}
