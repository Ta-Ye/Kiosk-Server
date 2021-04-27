package taye.kiosk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import taye.kiosk.domain.Menu;
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
	public ResponseEntity<List<Menu>> getFavoriteMenuList() {
		return new ResponseEntity<>(menuService.getFavoriteMenuList(), HttpStatus.OK);
	}

	@GetMapping("menu/favorite/age/{age}")
	public ResponseEntity<List<Menu>> getFavoriteMenuListByAge(@PathVariable("age") String age) {
		return new ResponseEntity<>(menuService.getFavoriteMenuListByAge(age), HttpStatus.OK);
	}

	@GetMapping("menu/favorite/store/{key}")
	public ResponseEntity<List<Menu>> getFavoriteMenuListByStore(@PathVariable(" key") String key) {
		return new ResponseEntity<>(
				menuService.getFavoriteMenuListByStore(storeService.getStoreByKey(key).getStoreNum()), HttpStatus.OK);
	}

	@PostMapping("order/insert")
	public void insertOrder(@RequestBody OrderRegi orderRegi) {
		orderService.insertOrder(menuService.getMenuListById(orderRegi.getOrder()),
				storeService.getStoreByKey(orderRegi.getKey()), orderRegi.getAge());
	}
}
