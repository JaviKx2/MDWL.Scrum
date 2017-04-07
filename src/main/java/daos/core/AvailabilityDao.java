package daos.core;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.core.Availability;

public interface AvailabilityDao extends JpaRepository<Availability, Long> {

}
