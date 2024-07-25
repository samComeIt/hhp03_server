package hh.plus.server.orderSheet;

import hh.plus.server.order.domain.repository.OrderSheetRepository;
import hh.plus.server.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderSheetServiceTest {
    @Mock
    private OrderSheetRepository orderSheetRepository;

    @InjectMocks
    private OrderService orderService;


    @Test
    @DisplayName("주문서 생성 실패 케이스")
    public void testCreateOrderSheetFail() {

    }

}
