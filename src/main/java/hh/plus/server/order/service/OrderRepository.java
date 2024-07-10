package hh.plus.server.order.service;

import hh.plus.server.order.domain.entity.Order;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);

    Optional<Order> findById(Long orderId);
}
