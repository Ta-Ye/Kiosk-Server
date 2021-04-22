package taye.kiosk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Menu {
	
	@Id
	@Column(name = "MENU_NUM")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menuNum;
	
	@Column(name = "MENU_NAME")
	private String menuName;
	
	@Column(name = "PRICE")
	private int price;
	
	@ManyToMany(mappedBy = "menus")
	private List<Order> orders = new ArrayList<>();
}