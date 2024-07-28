package hh.plus.server.order.domain.repository;

import hh.plus.server.order.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    Order save(Order order);

    Optional<Order> findById(Long orderId);
}
