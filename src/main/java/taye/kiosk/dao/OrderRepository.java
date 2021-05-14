package taye.kiosk.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import taye.kiosk.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
	@Query(value = "SELECT * FROM ORDERING WHERE ORDERING.STORE_NUM = :STORENUM AND ORDERING.ORDER_DATE >= :NOWDATE", nativeQuery = true)
	List<Order> findMonthlyOrders(@Param("STORENUM") Long storeNum, @Param("NOWDATE") LocalDate nowdate);
}
