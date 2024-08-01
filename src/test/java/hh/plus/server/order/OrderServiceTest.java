package hh.plus.server.order;

import hh.plus.server.order.domain.entity.Order;
import hh.plus.server.order.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    @DisplayName("주문 생성 성공 케이스")
    public void testCreateOrderSuccess()
    {
//        // given
//        List<OrderDetail> orderDetail = Arrays.asList(
//          new OrderDetail()
//        );
//
//        Order order = new Order(101L, "INCOMPLETE", orderDetail, LocalDateTime.now(), LocalDateTime.now());
//
//        // when
//        Order mockOrder = orderService.createOrder(order);
//
//        // then
//        assertNotNull(mockOrder);
    }

}
