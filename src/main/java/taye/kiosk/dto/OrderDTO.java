package taye.kiosk.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import taye.kiosk.domain.Menu;
import taye.kiosk.domain.Order;

public class OrderDTO {
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class OrderRegi {
		
		private List<Long> order;
		private String age;
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@Builder
	public static class OrderDetail {
		
		private String age;
		private LocalDate date;
		private String menus;
		private int price;
		
		public static OrderDetail of(Order order) {
			
			int price = 0;
			String menus = "";
			for(Menu menu : order.getMenus()) {
				price += menu.getPrice();
				menus += menu.getMenuName() + ", ";
			}
			return OrderDetail.builder().age(order.getAge()).date(order.getOrderDate()).menus(menus.substring(0, menus.length()-2)).price(price).build(); 
		}
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@Builder
	public static class OrderSale {
		
		private int x;
		private int y;
	}
}
