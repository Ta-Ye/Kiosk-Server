package taye.kiosk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import taye.kiosk.dto.StoreDTO.StoreRegi;
import taye.kiosk.service.StoreService;

@Controller
@RequiredArgsConstructor
public class BackOfficeController {
	
	private final StoreService storeService;
	
	@GetMapping("/")
	public String home() {
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
	
	@GetMapping("/error")
	public String error() {
		return "Error";
	}
}
