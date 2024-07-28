package hh.plus.server.payment.controller;

import hh.plus.server.payment.domain.PaymentStatus;
import hh.plus.server.payment.service.dto.PaymentDto;
import hh.plus.server.payment.service.dto.PaymentRequestDto;
import hh.plus.server.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Payment API")
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Create payment", description = "Create a payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "Not found - The product does not exist"),
    })
    @PostMapping("/{order_id}")
    public PaymentDto addPayment(@PathVariable long order_id, PaymentRequestDto paymentRequestDto){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setOrderId(order_id);
        return paymentService.addPayment(paymentDto);
    }

    @Operation(summary = "Update payment status", description = "Update a payment status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Not found - The payment does not exist"),
    })
    @PatchMapping("/{payment_id}")
    public void updatePayment(@PathVariable long payment_id, @RequestBody PaymentStatus status){
            paymentService.updatePayment(payment_id, status);
    }
}
