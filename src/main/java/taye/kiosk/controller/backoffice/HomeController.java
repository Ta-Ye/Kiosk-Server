package taye.kiosk.controller.backoffice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import taye.kiosk.domain.Menu;
import taye.kiosk.service.MenuService;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final MenuService menuService;
	
	@GetMapping("/age")
	public String charts() {
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
