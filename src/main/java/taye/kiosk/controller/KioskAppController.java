package taye.kiosk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import taye.kiosk.domain.Menu;
import taye.kiosk.domain.Store;
import taye.kiosk.dto.OrderDTO.OrderRegi;
import taye.kiosk.service.MenuService;
import taye.kiosk.service.OrderService;
import taye.kiosk.service.StoreService;

@RestController
@RequestMapping("/api/")
public class KioskAppController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private MenuService menuService;

	@GetMapping("menu/favorite")
	public List<Menu> getFavoriteMenuList() {
		return menuService.getFavoriteMenuList();
	}

	@GetMapping("menu/favorite/age/{age}")
	public ResponseEntity<List<Menu>> getFavoriteMenuListByAge(@PathVariable("age") String age) {
		return new ResponseEntity<>(menuService.getFavoriteMenuListByAge(age), HttpStatus.OK);
	}

	@GetMapping("menu/favorite/store")
	public ResponseEntity<List<Menu>> getFavoriteMenuListByStore(@AuthenticationPrincipal Store store) {
		return new ResponseEntity<>(
				menuService.getFavoriteMenuListByStore(store.getStoreNum()), HttpStatus.OK);
	}

	@PostMapping("order/insert")
	public void insertOrder(@AuthenticationPrincipal Store store, @RequestBody OrderRegi orderRegi) {
		orderService.insertOrder(menuService.getMenuListById(orderRegi.getOrder()),
				storeService.findStoreById(store.getStoreNum()).get(), orderRegi.getAge());
	}
	
	@GetMapping("authcheck")
	public boolean authCheck() {
		return true;
	}
}
