package taye.kiosk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import taye.kiosk.domain.Order;
import taye.kiosk.service.OrderService;

@RestController("order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/insert")
	public void insertOrder(@RequestBody Order order) {
		orderService.insertOrder(order);
	}
	
	
}
