package taye.kiosk.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import taye.kiosk.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
