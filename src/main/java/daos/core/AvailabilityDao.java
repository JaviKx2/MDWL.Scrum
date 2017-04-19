package daos.core;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entities.core.Availability;
import entities.core.Room;

public interface AvailabilityDao extends JpaRepository<Availability, Long> {

    @Query("SELECT a FROM Availability a WHERE a.room.id = ?1 " + "AND a.startDate <= ?2 " + "AND a.endingDate >= ?3")
    List<Availability> findIfRoomIsAvailable(long roomId, Date entryDate, Date departureDate);

    List<Availability> findByRoomAndStartDate(Room room, Date startDate);

    List<Availability> findByRoomAndEndingDate(Room room, Date endingDate);
    
    @Query("SELECT a FROM Availability a WHERE (:hotelName IS NULL OR a.room.hotel.name LIKE CONCAT('%',:hotelName,'%')) "
            + "AND (:city IS NULL OR a.room.hotel.city LIKE CONCAT('%',:city,'%')) "
            + "AND (:postCode IS NULL OR a.room.hotel.postcode = :postCode) "
            + "AND (:slotStartDate IS NULL OR :slotEndDate IS NULL "
                    + "OR (a.endingDate >= :slotEndDate AND a.startDate < :slotEndDate) "
                    + "OR (a.endingDate < :slotEndDate AND a.endingDate > :slotStartDate))")
    List<Availability> search(@Param("hotelName") String hotelName, @Param("city") String city, @Param("postCode") String postCode, @Param("slotStartDate") Date slotStartDate, @Param("slotEndDate") Date slotEndDate);
}
