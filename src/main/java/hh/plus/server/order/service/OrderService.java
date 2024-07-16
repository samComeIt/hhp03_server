package hh.plus.server.order.service;

import hh.plus.server.order.config.OrderStatus;
import hh.plus.server.order.controller.dto.OrderCreateRequestDto;
import hh.plus.server.order.controller.dto.request.OrderSheetItemRequestDto;
import hh.plus.server.order.controller.dto.request.OrderSheetRequestDto;
import hh.plus.server.order.domain.entity.Order;
import hh.plus.server.order.domain.entity.OrderDetail;
import hh.plus.server.order.domain.entity.OrderSheet;
import hh.plus.server.order.domain.entity.OrderSheetItem;
import hh.plus.server.order.domain.repository.OrderRepository;
import hh.plus.server.order.domain.repository.OrderSheetRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderSheetRepository orderSheetRepository;

    @Transactional
    public Order createOrder(Order orderRequest)
    {
        log.info("OrderCreateRequestDto : {}", orderRequest);

        Order order = new Order(orderRequest.getStatus(), orderRequest.getOrderDetail(), LocalDateTime.now(), LocalDateTime.now());
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

    @Transactional(readOnly = true)
    public Order getOrderById(Long orderId)
    {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        return order;
    }

    @Transactional
    public OrderSheet createOrderSheet(OrderSheet orderSheetRequest)
    {
        OrderSheet orderSheet = new OrderSheet(orderSheetRequest.getOrderSheetItem(), LocalDateTime.now());

        for (OrderSheetItem orderSheetItem : orderSheetRequest.getOrderSheetItem()) {
            orderSheetItem.setOrderSheet(orderSheet);
        }
        return orderSheetRepository.save(orderSheet);
    }

    @Transactional(readOnly = true)
    public OrderSheet findOrderSheetById(Long orderSheetId)
    {
        OrderSheet orderSheet = orderSheetRepository.findById(orderSheetId)
                .orElseThrow(() -> new IllegalArgumentException("Order Sheet not found"));

        return orderSheet;
    }

    @Transactional
    public void deleteOrderSheet(Long orderSheetId)
    {
        OrderSheet orderSheet = orderSheetRepository.findById(orderSheetId)
                .orElseThrow(() -> new IllegalArgumentException("Order Sheet not found"));

        orderSheetRepository.delete(orderSheet);
    }
}
