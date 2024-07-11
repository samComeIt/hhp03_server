package hh.plus.server.order.domain.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import hh.plus.server.payment.domain.entity.Payment;
import hh.plus.server.product.domain.entity.ProductOption;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "`ORDER`", indexes = {
        @Index(name="idx_order", columnList = "orderId")
})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderDetail> orderDetail = new ArrayList<>();

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Order(Long orderId, String status, List<OrderDetail> orderDetail, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.status = status;
        this.orderDetail = orderDetail;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
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
