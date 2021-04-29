package taye.kiosk.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class OrderDTO {
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class OrderRegi {
		
		private List<Long> order;
		private String age;
	}
}
