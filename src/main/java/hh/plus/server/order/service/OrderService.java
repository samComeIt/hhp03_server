package hh.plus.server.order.service;

import hh.plus.server.order.domain.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order createOrder(Order order)
    {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long orderId, String status)
    {
        Order order = orderRepository.findById(orderId).get();
        return order;
    }
}
