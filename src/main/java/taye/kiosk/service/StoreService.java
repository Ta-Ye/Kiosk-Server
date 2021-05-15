package taye.kiosk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import taye.kiosk.dao.StoreRepository;
import taye.kiosk.domain.Store;
import taye.kiosk.dto.StoreDTO.StoreDetail;
import taye.kiosk.dto.StoreDTO.StoreName;
import taye.kiosk.dto.StoreDTO.StoreRegi;

@Transactional
@Service
public class StoreService implements UserDetailsService{
	
	@Autowired
	StoreRepository storeRepo;
	
	@Override
	public StoreDetail loadUserByUsername(String username) throws UsernameNotFoundException {
		return StoreDetail.of(storeRepo.findByStoreName(username)
				.orElseThrow(()-> new UsernameNotFoundException(username)));
	}
	
	public Optional<Store> findStore(String storeName){
		return storeRepo.findByStoreName(storeName);
	}
	
	public Store save(StoreRegi store) {
		return storeRepo.save(StoreRegi.toEntity(store));
	}
	
	public List<StoreName> getStoreNameList(){
		List<StoreName> names = new ArrayList<>();
		storeRepo.findAll().forEach(o -> names.add(StoreName.of(o)));
		return names;
	}
	
	public Optional<Store> findStoreById(Long id){
		return storeRepo.findById(id);
	}
	
	public void updateStore(Store store, StoreRegi storeRegi) {
		store.setStoreName(storeRegi.getId());
		store.setStoreKey(StoreRegi.toEntity(storeRegi).getStoreKey());
		storeRepo.save(store);
	}
	
	public void deleteStore(Store store) {
		storeRepo.delete(store);
	}
}
