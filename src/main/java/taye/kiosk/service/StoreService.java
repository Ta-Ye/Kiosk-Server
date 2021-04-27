package taye.kiosk.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import taye.kiosk.dao.StoreRepository;
import taye.kiosk.domain.Store;
import taye.kiosk.dto.StoreDTO.StoreDetail;
import taye.kiosk.dto.StoreDTO.StoreName;
import taye.kiosk.dto.StoreDTO.StoreRegi;

@Service
@Transactional
public class StoreService implements UserDetailsService{
	
	@Autowired
	StoreRepository storeRepo;
	
	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
	
	public Store save(StoreRegi store) {
		store.setPassword(passwordEncoder.encode(store.getPassword()));
		return storeRepo.save(StoreRegi.toEntity(store));
	}
	
	public List<StoreName> getStoreNameList(){
		List<StoreName> names = new ArrayList<>();
		storeRepo.findAll().forEach(o -> names.add(StoreName.of(o)));
		return names;
	}
}
