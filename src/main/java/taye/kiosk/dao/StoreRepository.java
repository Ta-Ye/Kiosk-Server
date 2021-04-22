package taye.kiosk.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import taye.kiosk.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
	
}
