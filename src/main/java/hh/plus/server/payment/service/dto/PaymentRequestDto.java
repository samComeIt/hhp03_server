package hh.plus.server.payment.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PaymentRequestDto {
    private Long paymentId;
    private Long amount;
    private String type;
    private String method_type;
    private String status;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    @Builder
    public PaymentRequestDto(Long paymentId, Long amount,
                      String type, String method_type, String status, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.type = type;
        this.method_type = method_type;
        this.status = status;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }
}
