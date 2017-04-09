package daos.core;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.core.Reservation;

public interface ReservationDao extends JpaRepository<Reservation, Long> {

}
