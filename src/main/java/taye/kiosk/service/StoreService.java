package taye.kiosk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taye.kiosk.dao.StoreRepository;
import taye.kiosk.domain.Store;

@Service
public class StoreService {
	
	@Autowired
	StoreRepository storeRepo;
	
	public Store getStoreByKey(String key) {
		return storeRepo.findByStoreKey();
	}
}
