package daos.core;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.core.HotelChain;

public interface HotelChainDao extends JpaRepository<HotelChain, Long> {

}
