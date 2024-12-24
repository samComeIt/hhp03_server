package hh.plus.server.order.service;

import hh.plus.server.balance.service.BalanceService;
import hh.plus.server.balance.service.dto.BalanceResponseDto;
import hh.plus.server.cart.service.CartService;
import hh.plus.server.cart.service.dto.Cart02Dto;
import hh.plus.server.common.exception.CustomException;
import hh.plus.server.order.domain.OrderStatus;
import hh.plus.server.order.domain.entity.Order;
import hh.plus.server.order.domain.entity.OrderItem;
import hh.plus.server.order.domain.entity.OrderSheet;
import hh.plus.server.order.domain.entity.OrderSheetItem;
import hh.plus.server.order.domain.listener.OrderCreatedEvent;
import hh.plus.server.order.domain.repository.OrderRepository;
import hh.plus.server.order.domain.repository.OrderSheetRepository;
import hh.plus.server.order.service.dto.request.OrderCreateRequestDto;
import hh.plus.server.order.service.dto.request.OrderItemCreateRequestDto;
import hh.plus.server.order.service.dto.request.OrderSheetCreateRequestDto;
import hh.plus.server.payment.domain.PaymentStatus;
import hh.plus.server.payment.domain.entity.Payment;
import hh.plus.server.payment.domain.repository.PaymentRepository;
import hh.plus.server.payment.service.PaymentService;
import hh.plus.server.payment.service.dto.PaymentDto;
import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.service.ProductService;
import hh.plus.server.product.service.dto.ProductDto;
import org.aspectj.weaver.ast.Or;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderSheetRepository orderSheetRepository;
    private final CartService cartService;
    private final BalanceService balanceService;
    private final ProductService productService;
    private final PaymentRepository paymentRepository;
    private ApplicationEventPublisher eventPublisher;

    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(OrderCreateRequestDto orderCreateRequestDto)
    {
        // 잔액 확인

        // 상품 존재 확인
        // 상품 재고 확인
        // 주문 생성

        // 잔액 확인
        // 결제 처리
        // 잔액 업데이트
        // 상품 재고 업데이트 처리

        // 주문 상태 업데이트 생성

        // 카트 삭제 처리

        BalanceResponseDto balance = balanceService.getBalanceByBalanceId(orderCreateRequestDto.balanceId());

        Long totalPrice = 0L;

        Order order = new Order();

        for(OrderItemCreateRequestDto orderItemRequest : orderCreateRequestDto.orderItemCreateRequestDto()) {
            Long productId = orderItemRequest.productId();
            Long productOptionId = orderItemRequest.productOptionId();
            Long quantity = orderItemRequest.quantity();

            ProductOption productOption = productService.getOptionById(productOptionId);
            productOption.isStockAvailable(quantity);

            totalPrice += orderItemRequest.totalPrice();

            order.addItem(new OrderItem(
                    order,
                    OrderStatus.PENDING,
                    orderItemRequest.quantity(),
                    orderItemRequest.totalPrice(),
                    orderItemRequest.singlePrice(),
                    orderItemRequest.productName(),
                    orderItemRequest.productOptionName(),
                    orderItemRequest.productId(),
                    orderItemRequest.productOptionId(),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            ));
        }

        orderRepository.save(order);

        Payment payment = new Payment(
                order.getOrderId(),
                "ORDER",
                "POINT",
                PaymentStatus.PENDING,
                totalPrice,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        paymentRepository.save(payment);

        if (balance.balance() < totalPrice) throw new CustomException("Not enough balance");

        payment.updateStatus(PaymentStatus.COMPLETED);
        paymentRepository.save(payment);

        order.updateStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);

        for(OrderItemCreateRequestDto orderItemRequest : orderCreateRequestDto.orderItemCreateRequestDto()) {
            Long productOptionId = orderItemRequest.productOptionId();
            Long quantity = orderItemRequest.quantity();

            productService.updateStockById(productOptionId, quantity);
        }

        for(OrderItemCreateRequestDto orderItemRequest : orderCreateRequestDto.orderItemCreateRequestDto()) {
            Long cartId = orderItemRequest.cartId();

            cartService.deleteCart(cartId);
        }

        eventPublisher.publishEvent(new OrderCreatedEvent(order.getOrderId()));

        return order;
    }

    @Transactional
    @CacheEvict(value = "orderCache", key = "#orderId")
    public Order updateOrder(Long orderId, OrderStatus status)
    {
        Order order = orderRepository.findById(orderId)
                        .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.updateStatus(status);
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "orderCache", key = "#orderId")
    public Order getOrderById(Long orderId)
    {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        return order;
    }

    @Transactional(rollbackFor = Exception.class)
    public OrderSheet createOrderSheet(OrderSheetCreateRequestDto orderSheetCreateRequestDto)
    {
        // check cart(s) exist
        // create order sheet

        List<Cart02Dto> carts = new ArrayList<>();

        for (Long cartId : orderSheetCreateRequestDto.cartId()) {
            Cart02Dto cart = cartService.getCart02ById(cartId);
            carts.add(cart);
        }

        OrderSheet orderSheet = new OrderSheet();

        for (Cart02Dto cart: carts)
        {
            orderSheet.addItem(new OrderSheetItem(
                    orderSheet
                    , cart.cartId()
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
    @Cacheable(value = "orderSheetCache", key = "#criteria")
    public OrderSheet findOrderSheetById(Long orderSheetId)
    {
        OrderSheet orderSheet = orderSheetRepository.findById(orderSheetId)
                .orElseThrow(() -> new IllegalArgumentException("Order Sheet not found"));

        return orderSheet;
    }

    @Transactional
    @CacheEvict(value = "orderSheetCache", key = "#criteria")
    public void deleteOrderSheet(Long orderSheetId)
    {
        OrderSheet orderSheet = orderSheetRepository.findById(orderSheetId)
                .orElseThrow(() -> new IllegalArgumentException("Order Sheet not found"));

        orderSheetRepository.delete(orderSheet);
    }
}
