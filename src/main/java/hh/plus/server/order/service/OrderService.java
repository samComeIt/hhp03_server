package hh.plus.server.order.service;

import hh.plus.server.cart.domain.entity.Cart02;
import hh.plus.server.cart.service.CartService;
import hh.plus.server.cart.service.dto.Cart02Dto;
import hh.plus.server.cart.service.dto.Cart02DtoMapper;
import hh.plus.server.cart.service.dto.CartDtoMapper;
import hh.plus.server.order.domain.OrderStatus;
import hh.plus.server.order.domain.entity.Order;
import hh.plus.server.order.domain.entity.OrderDetail;
import hh.plus.server.order.domain.entity.OrderSheet;
import hh.plus.server.order.domain.entity.OrderSheetItem;
import hh.plus.server.order.domain.repository.OrderRepository;
import hh.plus.server.order.domain.repository.OrderSheetRepository;
import hh.plus.server.order.service.dto.request.OrderScheetCreateRequestDto;
import org.aspectj.weaver.ast.Or;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderSheetRepository orderSheetRepository;
    private final CartService cartService;

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

    @Transactional(rollbackFor = Exception.class)
    public OrderSheet createOrderSheet(OrderScheetCreateRequestDto orderScheetCreateRequestDto)
    {
        // check cart(s) exist
        // create order sheet

        List<Cart02Dto> carts = new ArrayList<>();

        for (Long cartId : orderScheetCreateRequestDto.cartId()) {
            Cart02Dto cart = cartService.getCart02ById(cartId);
            carts.add(cart);
        }

        OrderSheet orderSheet = new OrderSheet();

        for (Cart02Dto cart: carts)
        {
            orderSheet.addItem(new OrderSheetItem(
                    orderSheet
                    , OrderStatus.COMPLETED
                    , cart.quantity()
                    , cart.totalPrice()
                    , cart.singlePrice()
                    , cart.productName()
                    , cart.productOptionName()
                    , LocalDateTime.now()
                    , LocalDateTime.now()
                    , cart.productId()
                    , cart.productOptionId()
            ));
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
