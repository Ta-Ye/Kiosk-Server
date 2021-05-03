package taye.kiosk.controller.backoffice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/charts")
	public String charts() {
		return "charts";
	}
	
	@GetMapping("/tables")
	public String tables() {
		return "tables";
	}
	
	@GetMapping("/layout-static")
	public String layouts() {
		return "layout-static";
	}
}
