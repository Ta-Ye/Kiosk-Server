package taye.kiosk.controller.backoffice;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import taye.kiosk.domain.Store;
import taye.kiosk.dto.StoreDTO.StoreDetail;
import taye.kiosk.dto.StoreDTO.StoreRegi;
import taye.kiosk.service.OrderService;
import taye.kiosk.service.StoreService;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final StoreService storeService;
	private final OrderService orderService;
	
	
	@GetMapping("/")
	public String home(Model model) {
		StoreDetail storeDetail = (StoreDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Store store = storeService.findStore(storeDetail.getStoreName()).orElseThrow(() -> new UsernameNotFoundException(storeDetail.getStoreName()));
		
		// 일별 주문건수 추이
		model.addAttribute("orderperday", orderService.getOrdersPerDay(store));
		// 월별 매출액 추이
		model.addAttribute("salespermonth", orderService.getSalesPerMonth(store));
		// 올해 주문정보
		model.addAttribute("orders", orderService.getOrdersByYear(store));
		
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("storeList", storeService.getStoreNameList());
		return "loginForm";
	}
	
	@GetMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("storeList", storeService.getStoreNameList());
		model.addAttribute("loginError", true);
		return "loginForm";
	}
	
	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}
	
	@PostMapping("/signin")
	public String signin(StoreRegi store, Model model) {
		if (storeService.findStore(store.getId()).isEmpty()) {
			storeService.save(store);
			return "redirect:login";
		}
		model.addAttribute("signinError", true);
		return "signin";
	}
}
