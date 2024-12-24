package hh.plus.server.order.domain.repository;

import hh.plus.server.order.domain.OrderStatus;
import hh.plus.server.order.domain.entity.Order;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(Long orderId);

    Optional<Order> findByOrderIdAndStatus(Long orderId, OrderStatus status);
}
