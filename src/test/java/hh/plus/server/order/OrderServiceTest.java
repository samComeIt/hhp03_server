package hh.plus.server.order;

import hh.plus.server.balance.domain.repository.BalanceRepository;
import hh.plus.server.balance.service.BalanceService;
import hh.plus.server.order.domain.OrderStatus;
import hh.plus.server.order.domain.entity.Order;
import hh.plus.server.order.domain.listener.OrderCreatedEvent;
import hh.plus.server.order.domain.repository.OrderRepository;
import hh.plus.server.order.service.OrderService;
import hh.plus.server.order.service.dto.request.OrderCreateRequestDto;
import hh.plus.server.order.service.dto.request.OrderItemCreateRequestDto;
import hh.plus.server.payment.domain.repository.PaymentRepository;
import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @InjectMocks
    private BalanceService balanceService;

    @Mock
    private ApplicationEventPublisher eventPublisher;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }


    @Test
    @DisplayName("주문 생성 event listener 성공 케이스")
    public void testCreateOrderPublishEvent() {
        Long orderId = 1L;
        Long cartId = 1L;

        OrderItemCreateRequestDto orderItemCreateRequestDto = new OrderItemCreateRequestDto(
                cartId
                , 100L
                ,101L
                ,"Sample"
                , "Option 1"
                , 2L
                , 2000L
                , 4000L
        );

        List<OrderItemCreateRequestDto> orderItemList = Collections.singletonList(orderItemCreateRequestDto);

        OrderCreateRequestDto order = new OrderCreateRequestDto(
                100L
                , 1L
                , orderItemList
        );


        orderService.createOrder(order);

        verify(eventPublisher).publishEvent(new OrderCreatedEvent(orderId));
    }

}
