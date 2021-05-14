package taye.kiosk.controller.backoffice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/charts")
	public String charts() {
		return "charts";
	}
}
