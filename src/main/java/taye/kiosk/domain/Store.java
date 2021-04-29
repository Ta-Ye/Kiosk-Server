package taye.kiosk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Store{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STORE_NUM")
	private Long storeNum;
	
	@Column(name = "STORE_NAME")
	private String storeName;
	
	@Column(name = "STORE_LOCATION")
	private String location;
	
	@Column(name = "STORE_KEY")
	private String storeKey;
}
