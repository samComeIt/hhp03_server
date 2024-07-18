package hh.plus.server.order.domain.repository.impl;

import hh.plus.server.order.domain.entity.Order;
import hh.plus.server.order.domain.repository.OrderJpaRepository;
import hh.plus.server.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) { return orderJpaRepository.save(order);}

    @Override
    public Optional<Order> findById(Long orderId) { return orderJpaRepository.findById(orderId);}
}
