package taye.kiosk.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity(name = "ordering")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_NUM")
	private Long orderNum;

	@Column(name = "AGE")
	private String age;

	@Column(name = "ORDER_DATE")
	private LocalDate orderDate;

	@ManyToOne
	@JoinColumn(name = "STORE_NUM")
	private Store store;
	
	@ManyToMany
	@JoinTable(name = "ORDER_MENU")
	private List<Menu> menus;
}
