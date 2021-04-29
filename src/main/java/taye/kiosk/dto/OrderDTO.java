package taye.kiosk.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class OrderDTO {
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	public static class OrderRegi {
		
		private List<Long> order;
		private String age;
	}
}
