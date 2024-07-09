package hh.plus.server.order.controller;

import hh.plus.server.order.controller.dto.OrderCreateRequestDto;
import hh.plus.server.order.controller.dto.OrderCreateResponseDto;
import hh.plus.server.order.controller.dto.OrderResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@Tag(name = "Order API")
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    /**
     * 주문 조회
     * @param orderId
     * @return
     */
    @Operation(summary = "Get order by id", description = "Returns a order as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The order does not exist"),
    })
    @GetMapping("/{orderId}")
    public OrderResponseDto getOrder(@PathVariable long orderId){ return OrderResponseDto.response(orderId, Arrays.asList(100L, 101L));}

    /**
     * 주문 생성
     * @param orderCreateRequestDto
     * @return
     */
    @Operation(summary = "Create order", description = "Create an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "Not found - Missing data from request body"),
    })
    @PostMapping("/create")
    public OrderCreateResponseDto createOrder(@RequestBody OrderCreateRequestDto orderCreateRequestDto) {
        return OrderCreateResponseDto.response(100L, Arrays.asList(200L, 201L, 202L));}
}
