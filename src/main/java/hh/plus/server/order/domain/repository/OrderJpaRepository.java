package hh.plus.server.order.domain.repository;

import hh.plus.server.order.domain.OrderStatus;
import hh.plus.server.order.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    Order save(Order order);

    Optional<Order> findById(Long orderId);

    @Query("SELECT o FROM Order o WHERE o.orderId = :orderId AND o.status = :status")
    Optional<Order> findByOrderIdAndStatus(Long orderId, OrderStatus status);
}
