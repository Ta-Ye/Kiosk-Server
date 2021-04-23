package taye.kiosk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taye.kiosk.dao.MenuRepository;
import taye.kiosk.domain.Menu;

@Service
public class MenuService {
	
	@Autowired
	private MenuRepository menuRepo;
	
	// 인기 메뉴 3개
	public List<Menu> getFavoriteMenuList(){
		return menuRepo.findThreeOrder();
	}
	
	// 매장별 인기 메뉴 3개
	public List<Menu> getFavoriteMenuListByStore(Long storeNum){
		return menuRepo.findThreeOrderByStoreNum(storeNum);
	}
	
	// 연령별 인기 메뉴 3개
	public List<Menu> getFavoriteMenuListByAge(String age){
		return menuRepo.findThreeOrderByAge(age);
	}
}
