package hh.plus.server.controller.order;

import hh.plus.server.controller.order.dto.OrderCreateRequestDto;
import hh.plus.server.controller.order.dto.OrderCreateResponseDto;
import hh.plus.server.controller.order.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    /**
     * 주문 조회
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public OrderResponseDto getOrder(@PathVariable long orderId){ return OrderResponseDto.response(orderId);}

    /**
     * 주문 생성
     * @param orderCreateRequestDto
     * @return
     */
    @PostMapping("/create")
    public Long createOrder(@RequestBody OrderCreateRequestDto orderCreateRequestDto) {return OrderCreateResponseDto.response(orderCreateRequestDto);}
}
