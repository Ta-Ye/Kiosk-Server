package taye.kiosk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import taye.kiosk.domain.Menu;
import taye.kiosk.service.MenuService;
import taye.kiosk.service.StoreService;

@RestController("menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private StoreService storeService;
	
	@GetMapping("/favorite")
	public ResponseEntity<List<Menu>> getFavoriteMenuList() {
		return new ResponseEntity<>(menuService.getFavoriteMenuList(), HttpStatus.OK);
	}
	
	@GetMapping("/favorite/age/{age}")
	public ResponseEntity<List<Menu>> getFavoriteMenuListByAge(@PathVariable("age") String age) {
		return new ResponseEntity<>(menuService.getFavoriteMenuListByAge(age), HttpStatus.OK);
	}
	
	@GetMapping("/favorite/store/{key}")
	public ResponseEntity<List<Menu>> getFavoriteMenuListByStore(@PathVariable("key") String key) {
		return new ResponseEntity<>(menuService.getFavoriteMenuListByStore(storeService.getStoreByKey(key).getStoreNum()), HttpStatus.OK);
	}
}
