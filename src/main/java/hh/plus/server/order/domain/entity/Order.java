package hh.plus.server.order.domain.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import hh.plus.server.order.domain.OrderStatus;
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

    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderDetail> orderDetail = new ArrayList<>();

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Order(OrderStatus status, List<OrderDetail> orderDetail, LocalDateTime updatedAt, LocalDateTime createdAt) {
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
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

    public void updateStatus(OrderStatus newStatus)
    {
        this.status = newStatus;
    }
}
