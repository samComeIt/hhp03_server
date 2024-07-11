package hh.plus.server.order.service;

import hh.plus.server.order.domain.entity.Order;
import hh.plus.server.order.domain.entity.OrderDetail;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(Order order)
    {
        for (OrderDetail orderDetail : order.getOrderDetail()) {
            orderDetail.setOrder(order);
        }
        return orderRepository.save(order);
    }

    @Transactional
    public Order updateOrder(Long orderId, String status)
    {
        Order order = orderRepository.findById(orderId).get();
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
