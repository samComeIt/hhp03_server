package hh.plus.server.order.facade;

import hh.plus.server.balance.service.BalanceService;
import hh.plus.server.balance.service.dto.BalanceResponseDto;
import hh.plus.server.cart.service.CartService;
import hh.plus.server.common.exception.CustomException;
import hh.plus.server.order.domain.OrderStatus;
import hh.plus.server.order.domain.entity.Order;
import hh.plus.server.order.domain.entity.OrderItem;
import hh.plus.server.order.domain.repository.OrderRepository;
import hh.plus.server.order.service.OrderService;
import hh.plus.server.order.service.dto.request.OrderCreateRequestDto;
import hh.plus.server.order.service.dto.request.OrderItemCreateRequestDto;
import hh.plus.server.payment.domain.PaymentStatus;
import hh.plus.server.payment.domain.entity.Payment;
import hh.plus.server.payment.domain.repository.PaymentRepository;
import hh.plus.server.payment.service.PaymentService;
import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final ProductService productService;
    private final BalanceService balanceService;
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public Order createOrder(OrderCreateRequestDto orderCreateRequestDto) {
        BalanceResponseDto balance = balanceService.getBalanceByBalanceId(orderCreateRequestDto.balanceId());
        Long totalPrice = calculateTotalPrice(orderCreateRequestDto);

        Order order = createOrderEntity(orderCreateRequestDto);
        Payment payment = createPaymentEntity(order, totalPrice);

        // create order
        // create payment
        // update product stock, balance
        // delete cart, orderSheet

        processPayment(orderCreateRequestDto, order, payment, totalPrice, balance);
        deleteCartItem(orderCreateRequestDto);

        return order;
    }

    public Long calculateTotalPrice(OrderCreateRequestDto orderCreateRequestDto) {
        return orderCreateRequestDto.orderItemCreateRequestDto()
                .stream().mapToLong(OrderItemCreateRequestDto::totalPrice)
                .sum();
    }

    @Transactional
    public Order createOrderEntity(OrderCreateRequestDto orderCreateRequestDto) {
        Order order = new Order();

        for(OrderItemCreateRequestDto orderItemRequest : orderCreateRequestDto.orderItemCreateRequestDto()) {
            Long productOptionId = orderItemRequest.productOptionId();
            Long quantity = orderItemRequest.quantity();

            ProductOption productOption = productService.getOptionById(productOptionId);
            productOption.isStockAvailable(quantity);

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
        log.info("Order created : {}", order);
        return order;
    }

    public Payment createPaymentEntity(Order order, Long totalPrice) {
        Payment payment = new Payment(
                order.getOrderId(),
                "ORDER",
                "POINT",
                PaymentStatus.PENDING,
                totalPrice,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        log.info("Create Payment : {}", payment);
        paymentRepository.save(payment);
        return payment;
    }

    public void processPayment(OrderCreateRequestDto orderCreateRequestDto, Order order, Payment payment, Long totalPrice, BalanceResponseDto balance) {

        if (balance.balance() < totalPrice) throw new CustomException("Not enough balance");

        balanceService.updateBalance(balance.balanceId(), -totalPrice);

        for(OrderItemCreateRequestDto orderItemRequest : orderCreateRequestDto.orderItemCreateRequestDto()) {
            Long productOptionId = orderItemRequest.productOptionId();
            Long quantity = orderItemRequest.quantity();

            productService.updateStockById(productOptionId, quantity);
        }

        payment.updateStatus(PaymentStatus.COMPLETED);
        paymentRepository.save(payment);

        order.updateStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);

        balanceService.updateBalance(balance.balanceId(), totalPrice);
    }

    public void deleteCartItem(OrderCreateRequestDto orderCreateRequestDto) {
        for(OrderItemCreateRequestDto orderItemRequest : orderCreateRequestDto.orderItemCreateRequestDto()) {
            Long cartId = orderItemRequest.cartId();

            cartService.deleteCart(cartId);
        }
    }
}
