package hh.plus.server.order.controller;

import hh.plus.server.balance.service.BalanceService;
import hh.plus.server.order.config.OrderStatus;
import hh.plus.server.order.controller.dto.OrderCreateRequestDto;
import hh.plus.server.order.controller.dto.OrderCreateResponseDto;
import hh.plus.server.order.controller.dto.OrderResponseDto;
import hh.plus.server.order.domain.entity.Order;
import hh.plus.server.order.domain.entity.OrderSheet;
import hh.plus.server.order.service.OrderService;
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
    private final OrderService orderService;
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
    public Order getOrder(@PathVariable long orderId){ return orderService.getOrderById(orderId);}

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
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order); }

    @Operation(summary = "Update order status", description = "Update an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Not found - Missing data from request body"),
    })
    @PatchMapping("/{order_id}")
    public Order updateOrder(@PathVariable long orderId, @RequestBody OrderStatus status) {
        return orderService.updateOrder(orderId, status); }
}
