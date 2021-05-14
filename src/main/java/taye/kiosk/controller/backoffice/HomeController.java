package taye.kiosk.controller.backoffice;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import taye.kiosk.domain.Menu;
import taye.kiosk.domain.Store;
import taye.kiosk.dto.StoreDTO.StoreDetail;
import taye.kiosk.service.MenuService;
import taye.kiosk.service.OrderService;
import taye.kiosk.service.StoreService;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final MenuService menuService;
	private final OrderService orderService;
	private final StoreService storeService;
	
	@GetMapping("/age")
	public String charts(Model model) {
		StoreDetail storeDetail = (StoreDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Store store = storeService.findStore(storeDetail.getStoreName()).orElseThrow(() -> new UsernameNotFoundException(storeDetail.getStoreName()));
		List<Menu> menus = menuService.getAllMenuList();
		List<String> menuNames = orderService.getMenuNames(menus);
		
		model.addAttribute("menuNames", menuNames);
		model.addAttribute("sales", orderService.getOrdersPriceByAgeNum(orderService.getOrdersByAge(store,menus), menuNames));
		model.addAttribute("prices", orderService.getOrdersPriceByAgeNum(orderService.getOrderPriceByAge(store,menus), menuNames));
		return "charts";
	}
	
	@GetMapping("/menu")
	public String menus(Model model) {
		model.addAttribute("menus", menuService.getAllMenuList());
		return "menu";
	}
	
	@PostMapping("/menu")
	public String menuInsert(Model model, Menu menu) {
		
		try {
			Menu existMenu = menuService.findMenuById(menu).orElse(null);
			if (existMenu==null) {
				menuService.insertMenu(menu);
			} else {
				existMenu.setMenuName(menu.getMenuName());
				existMenu.setPrice(menu.getPrice());
				menuService.insertMenu(existMenu);
			}
		} catch(Exception e) {
			model.addAttribute("error", true);
		}
		model.addAttribute("menus", menuService.getAllMenuList());
		return "menu";
	}
}
