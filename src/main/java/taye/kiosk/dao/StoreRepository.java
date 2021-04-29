package taye.kiosk.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import taye.kiosk.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
	
	Optional<Store> findByStoreName(String storeName);
	
	Optional<Store> findStoreByStoreKey(String storeKey);
	
	Optional<Store> findByStoreNameAndStoreKey(String storeName, String storeKey);
}
