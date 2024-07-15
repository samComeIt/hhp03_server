package hh.plus.server.order.service;

import hh.plus.server.order.config.OrderStatus;
import hh.plus.server.order.controller.dto.request.OrderSheetItemRequestDto;
import hh.plus.server.order.controller.dto.request.OrderSheetRequestDto;
import hh.plus.server.order.domain.entity.Order;
import hh.plus.server.order.domain.entity.OrderDetail;
import hh.plus.server.order.domain.entity.OrderSheet;
import hh.plus.server.order.domain.entity.OrderSheetItem;
import hh.plus.server.order.domain.repository.OrderRepository;
import hh.plus.server.order.domain.repository.OrderSheetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderSheetRepository orderSheetRepository;

    @Transactional
    public Order createOrder(Order order)
    {
        for (OrderDetail orderDetail : order.getOrderDetail()) {
            orderDetail.setOrder(order);
        }
        return orderRepository.save(order);
    }

    @Transactional
    public Order updateOrder(Long orderId, OrderStatus status)
    {
        Order order = orderRepository.findById(orderId)
                        .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.updateStatus(status);
        return orderRepository.save(order);
    }

    @Transactional
    public OrderSheet createOrderSheet(OrderSheetRequestDto orderSheetRequestDto)
    {
        OrderSheet orderSheet = new OrderSheet();
        orderSheet.setCreatedAt(LocalDateTime.now());


        for (OrderSheetItemRequestDto itemRequestDto : orderSheetRequestDto.getOrderSheetItem()) {
            OrderSheetItem orderSheetItem = new OrderSheetItem();
            //orderSheetItem.setStatus(itemRequestDto.get);
        }
        return orderSheetRepository.save(orderSheet);
    }

    public OrderSheet findOrderSheetById(Long orderSheetId)
    {
        OrderSheet orderSheet = orderSheetRepository.findById(orderSheetId)
                .orElseThrow(() -> new IllegalArgumentException("Order Sheet not found"));

        return orderSheet;
    }

    public void deleteOrderSheet(Long orderSheetId)
    {
        OrderSheet orderSheet = orderSheetRepository.findById(orderSheetId)
                .orElseThrow(() -> new IllegalArgumentException("Order Sheet not found"));

        orderSheetRepository.delete(orderSheet);
    }
}
