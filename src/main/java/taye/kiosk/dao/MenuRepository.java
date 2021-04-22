package taye.kiosk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import taye.kiosk.domain.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long>{
	
	// 전체 인기 메뉴 3개
	@Query(value = "SELECT MENU.*"
			+ "FROM MENU,"
			+ " ( SELECT MENUS_MENU_NUM, COUNT(MENUS_MENU_NUM)"
			+ " FROM ORDER_MENU"
			+ " GROUP BY MENU_NUM"
			+ " ORDER BY COUNT(MENUS_MENU_NUM) DESC"
			+ " LIMIT 3) CNT"
			+ " WHERE MENU.MENU_NUM = CNT.MENUS_MENU_NUM", nativeQuery = true)
	List<Menu> findThreeOrder();
	
	// 연령별 인기 메뉴 3개
	@Query(value = "SELECT MENU.*"
			+ "FROM MENU,"
			+ " ( SELECT MENUS_MENU_NUM, COUNT(MENUS_MENU_NUM)"
			+ " FROM ( SELECT ORDER_MENU.* FROM ORDERING, ORDER_MENU WHERE ORDERING.AGE := AGE AND ORDER_MENU.ORDERS_ORDER_NUM = ORDERING.ORDER_NUM)"
			+ " GROUP BY MENU_NUM"
			+ " ORDER BY COUNT(MENUS_MENU_NUM) DESC"
			+ " LIMIT 3) CNT"
			+ " WHERE MENU.MENU_NUM = CNT.MENUS_MENU_NUM", nativeQuery = true)
	List<Menu> findThreeOrderByAge(@Param("AGE") String age);
	
	// 매장별 인기 메뉴 3개
	@Query(value = "SELECT MENU.*"
			+ "FROM MENU,"
			+ " ( SELECT MENUS_MENU_NUM, COUNT(MENUS_MENU_NUM)"
			+ " FROM ( SELECT ORDER_MENU.* FROM ORDERING, ORDER_MENU WHERE ORDERING.STORE_NUM := STORENUM AND ORDER_MENU.ORDERS_ORDER_NUM = ORDERING.ORDER_NUM)"
			+ " GROUP BY MENU_NUM"
			+ " ORDER BY COUNT(MENUS_MENU_NUM) DESC"
			+ " LIMIT 3) CNT"
			+ " WHERE MENU.MENU_NUM = CNT.MENUS_MENU_NUM", nativeQuery = true)
	List<Menu> findThreeOrderByStoreNum(@Param("STORENUM") Long storeNum);
}
