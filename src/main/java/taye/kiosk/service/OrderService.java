package taye.kiosk.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import taye.kiosk.dao.OrderRepository;
import taye.kiosk.domain.Menu;
import taye.kiosk.domain.Order;
import taye.kiosk.domain.Store;
import taye.kiosk.dto.OrderDTO.OrderDetail;
import taye.kiosk.dto.OrderDTO.OrderSale;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	public Order insertOrder(List<Menu> menus, Store store, String age) {
		return orderRepo.save(Order.builder().age(age).orderDate(LocalDate.now()).store(store).menus(menus).build());
	}
	
	private List<Order> getSortedOrders(Store store, LocalDate date) {
		List<Order> orders = orderRepo.findMonthlyOrders(store.getStoreNum(), date);
		orders.sort((a,b) -> a.getOrderDate().compareTo(b.getOrderDate()));
		return orders;
	}
	
	public List<OrderSale> getOrdersPerDay(Store store) {
		
		LocalDate date = LocalDate.now();
		List<Order> orders = getSortedOrders(store, LocalDate.of(date.getYear(), date.getMonth(), 1));
		
		int[] days = new int[31];
		orders.forEach(o -> days[o.getOrderDate().getDayOfMonth()-1]++);
		
		List<OrderSale> ans = new ArrayList<>();
		for (int i=0 ; i<31; i++) {
			if (days[i]>0) {
				ans.add(new OrderSale(i+1, days[i]));
			}
		}
		return ans;
	}
	
	public int[] getSalesPerMonth(Store store) {
		
		LocalDate date = LocalDate.now();
		List<Order> orders = getSortedOrders(store, LocalDate.of(date.getYear(), 1, 1));
		
		int[] months = new int[12];
		orders.forEach(o -> {
			int sum = 0;
			for (Menu menu : o.getMenus()) {
				sum+=menu.getPrice();
			}
			months[o.getOrderDate().getMonthValue()-1]+=sum;
		});
		
		return months;
	}
	
	public List<OrderDetail> getOrdersByYear(Store store) {
		
		List<OrderDetail> details = new ArrayList<>();
		LocalDate date = LocalDate.now();
		for (Order order : getSortedOrders(store, LocalDate.of(date.getYear(), 1, 1))){
			details.add(OrderDetail.of(order));
		}
		return details;
	}
}
