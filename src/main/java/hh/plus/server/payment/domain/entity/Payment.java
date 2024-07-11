package hh.plus.server.payment.domain.entity;

import hh.plus.server.order.domain.entity.Order;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "payment", indexes = {
        @Index(name="idx_payment", columnList = "paymentId")
})
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private Long orderId;
    private String type;
    private String methodType;
    private String status;
    private Long amount;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Payment(Long paymentId, Long orderId, String type, String methodType, String status, Long amount, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.type = type;
        this.methodType = methodType;
        this.status = status;
        this.amount = amount;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
