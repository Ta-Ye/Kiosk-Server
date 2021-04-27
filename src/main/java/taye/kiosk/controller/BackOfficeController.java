package taye.kiosk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
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
}
