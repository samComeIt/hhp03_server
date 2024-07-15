package hh.plus.server.order.controller;


import hh.plus.server.order.controller.dto.request.OrderSheetRequestDto;
import hh.plus.server.order.domain.entity.OrderSheet;
import hh.plus.server.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Tag(name = "Order Sheet API")
@RequestMapping("/api/orderSheet")
@RequiredArgsConstructor
public class OrderSheetController {

    private final OrderService orderService;

    /**
     * 주문서 생성
     * @param orderSheetRequestDto
     * @return
     */
    @Operation(summary = "Create order sheet", description = "Create an order sheet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The order sheet does not exist"),
    })
    @PostMapping("/create")
    public OrderSheet getOrderSheet(@RequestBody OrderSheetRequestDto orderSheetRequestDto){
        return orderService.createOrderSheet(orderSheetRequestDto); }

    /**
     * 주문서 조회
     * @param order_sheet_id
     * @return
     */
    @Operation(summary = "Get order sheet by id", description = "Returns an order sheet as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The order sheet does not exist"),
    })
    @GetMapping("/{order_sheet_id}")
    public OrderSheet getOrderSheet(@PathVariable long order_sheet_id){
        return orderService.findOrderSheetById(order_sheet_id); }

    /**
     * 주문서 조회
     * @param order_sheet_id
     * @return
     */
    @Operation(summary = "Delete order sheet by id", description = "Delete an order sheet as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The order sheet does not exist"),
    })
    @DeleteMapping("/{order_sheet_id}")
    public void deleteOrderSheet(@PathVariable long order_sheet_id){orderService.deleteOrderSheet(order_sheet_id); }
}
