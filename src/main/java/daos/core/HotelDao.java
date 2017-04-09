package daos.core;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.core.Hotel;

public interface HotelDao extends JpaRepository<Hotel, Long> {

}
