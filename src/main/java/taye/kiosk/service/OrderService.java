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

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	public Order insertOrder(List<Menu> menus, Store store, String age) {
		return orderRepo.save(Order.builder().age(age).orderDate(LocalDate.now()).store(store).menus(menus).build());
	}
	
	public List<int[]> getSalesPerDay(List<Order> orders) {
		
		orders.sort((a,b) -> {
			return a.getOrderDate().compareTo(b.getOrderDate());
		 });
		
		int[] days = new int[31];
		orders.forEach(o -> days[o.getOrderDate().getDayOfMonth()-1]++);
		
		List<int[]> ans = new ArrayList<>();
		for (int i=0 ; i<31; i++) {
			if (days[i]>0) {
				int[] ck = {i+1, days[i]};
				ans.add(ck);
			}
		}
		return ans;
	}
	
	public List<int[]> getOrderPerDay(Store store) {
		
		LocalDate date = LocalDate.now();
		LocalDate firstDate = LocalDate.of(date.getYear(), date.getMonth(), 1);
		return getSalesPerDay(orderRepo.findMonthlyOrders(store.getStoreNum(), firstDate));
	}
}
