package hh.plus.server.orderSheet;

import hh.plus.server.orderSheet.controller.dto.OrderSheetDto;
import hh.plus.server.orderSheet.controller.dto.OrderSheetItemDto;
import hh.plus.server.orderSheet.domain.entity.OrderSheet;
import hh.plus.server.orderSheet.domain.entity.OrderSheetItem;
import hh.plus.server.orderSheet.domain.repository.OrderSheetRepository;
import hh.plus.server.orderSheet.service.OrderSheetService;
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
    private OrderSheetService orderSheetService;


    @Test
    @DisplayName("주문서 생성 실패 케이스")
    public void testCreateOrderSheetFail() {
        // given
        OrderSheet orderSheet = new OrderSheet();
        orderSheet.setCreatedAt(LocalDateTime.now());

        OrderSheetItem orderSheetItem = new OrderSheetItem();
        orderSheetItem.setOrderSheet(orderSheet);
        orderSheetItem.setStatus("ACTIVE");
        orderSheetItem.setTotalCnt(10L);
        orderSheetItem.setTotalPrice(2000L);
        orderSheetItem.setSinglePrice(200L);
        orderSheetItem.setPname("상품01");
        orderSheetItem.setPoptionName("옵션1");
        orderSheetItem.setCreatedAt(LocalDateTime.now());
        orderSheetItem.setProductId(100L);
        orderSheetItem.setProductOptionId(10L);
        orderSheet.setOrderSheetItem(Collections.singletonList(orderSheetItem));

        OrderSheetDto orderSheetDto = new OrderSheetDto();
        orderSheetDto.setOrderSheetId(101L);
        orderSheetDto.setCreatedAt(LocalDateTime.now());

        OrderSheetItemDto orderSheetItemDto = new OrderSheetItemDto();
        orderSheetItemDto.setStatus("ACTIVE");
        orderSheetItemDto.setTotalCnt(10L);
        orderSheetItemDto.setTotalPrice(2000L);
        orderSheetItemDto.setSinglePrice(200L);
        orderSheetItemDto.setPname("상품01");
        orderSheetItemDto.setPoptionName("옵션1");
        orderSheetItemDto.setCreatedAt(LocalDateTime.now());
        orderSheetItemDto.setProductId(100L);
        orderSheetItemDto.setProductOptionId(10L);

        orderSheetDto.setOrderSheetItem(Collections.singletonList(orderSheetItemDto));

        // when
        when(orderSheetRepository.save(orderSheet)).thenReturn(new OrderSheet());
        Optional<OrderSheet> orderSheet1 = orderSheetRepository.findById(orderSheet.getOrderSheetId());
        // then
        assertEquals(orderSheet1.get(), orderSheetDto.getOrderSheetItem());
    }

}
