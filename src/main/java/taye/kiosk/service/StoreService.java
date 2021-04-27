package taye.kiosk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import taye.kiosk.dao.StoreRepository;
import taye.kiosk.domain.Store;
import taye.kiosk.dto.StoreDTO.StoreDetail;
import taye.kiosk.dto.StoreDTO.StoreName;

@Service
@Transactional
public class StoreService implements UserDetailsService{
	
	@Autowired
	StoreRepository storeRepo;

	@Override
	public StoreDetail loadUserByUsername(String username) throws UsernameNotFoundException {
		StoreDetail detail = StoreDetail.of(storeRepo.findStoreByStoreName(username)
				.orElseThrow(()-> new UsernameNotFoundException(username)));
		return detail;
	}
	
	public Store getStoreByKey(String storekey) {
		return storeRepo.findStoreByStoreKey(storekey);
	};
	
	public Optional<Store> findStore(String storeName){
		return storeRepo.findStoreByStoreName(storeName);
	}
	
	public Store save(Store store) {
		return storeRepo.save(store);
	}
	
	public List<StoreName> getStoreNameList(){
		List<StoreName> names = new ArrayList<>();
		storeRepo.findAll().forEach(o -> names.add(StoreName.of(o)));
		return names;
	}
}
