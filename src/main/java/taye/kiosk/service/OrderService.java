package taye.kiosk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taye.kiosk.dao.OrderRepository;
import taye.kiosk.domain.Order;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	public void insertOrder(Order order) {
		orderRepo.save(order);
	}
}
