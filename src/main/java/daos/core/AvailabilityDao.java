package daos.core;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.Availability;
import entities.core.Room;

public interface AvailabilityDao extends JpaRepository<Availability, Long> {

    @Query("SELECT a FROM Availability a WHERE a.room.id = ?1 " + "AND a.startDate <= ?2 " + "AND a.endingDate >= ?3")
    List<Availability> findIfRoomIsAvailable(long roomId, Date entryDate, Date departureDate);

    List<Availability> findByRoomAndStartDate(Room room, Date startDate);

    List<Availability> findByRoomAndEndingDate(Room room, Date endingDate);
}
