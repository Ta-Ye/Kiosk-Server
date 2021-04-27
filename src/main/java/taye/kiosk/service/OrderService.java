package taye.kiosk.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taye.kiosk.dao.OrderRepository;
import taye.kiosk.domain.Menu;
import taye.kiosk.domain.Order;
import taye.kiosk.domain.Store;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	public Order insertOrder(List<Menu> menus, Store store, String age) {
		return orderRepo.save(Order.builder().age(age).orderDate(LocalDate.now()).store(store).menus(menus).build());
	}
}
