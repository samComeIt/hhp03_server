package hh.plus.server.payment.controller;

import hh.plus.server.payment.controller.dto.PaymentDto;
import hh.plus.server.payment.controller.dto.PaymentRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Payment API")
@RequestMapping("/api/payment")
public class PaymentController {

    @Operation(summary = "Create payment", description = "Create a payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "Not found - The product does not exist"),
    })
    @PostMapping("/{order_id}")
    public PaymentDto addPayment(@PathVariable long order_id, PaymentRequestDto paymentRequestDto){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setOrderId(order_id);
        return paymentDto;
    }

    @Operation(summary = "Update payment status", description = "Update a payment status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Not found - The payment does not exist"),
    })
    @PatchMapping("/{payment_id}")
    public PaymentDto updatePayment(@PathVariable long payment_id, @RequestBody String status){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId(payment_id);
        paymentDto.setStatus(status);
        return paymentDto;
    }
}
