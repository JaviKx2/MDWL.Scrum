package daos.core;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.core.Room;

public interface RoomDao extends JpaRepository<Room, Long> {

}
