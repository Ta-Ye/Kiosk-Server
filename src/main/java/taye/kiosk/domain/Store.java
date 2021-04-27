package taye.kiosk.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
	
	@OneToMany(mappedBy = "store", fetch=FetchType.LAZY)
	private List<Order> orders;
}
