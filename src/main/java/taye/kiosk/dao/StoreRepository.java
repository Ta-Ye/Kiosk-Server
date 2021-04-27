package taye.kiosk.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import taye.kiosk.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
	
	Optional<Store> findStoreByStoreName(String storeName);
	
	Store findStoreByStoreKey(String storeKey);
}
