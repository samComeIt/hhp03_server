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
@Table(name = "`ORDER`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderItem> orderItem = new ArrayList<>();

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Order(OrderStatus status, List<OrderItem> orderItem, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.status = status;
        this.orderItem = orderItem;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void updateStatus(OrderStatus newStatus)
    {
        this.status = newStatus;
    }

    public void addItem(OrderItem item)
    {
        orderItem.add(item);
    }
}
