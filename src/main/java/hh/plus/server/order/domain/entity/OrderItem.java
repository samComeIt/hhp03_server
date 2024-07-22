package hh.plus.server.order.domain.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import hh.plus.server.order.domain.OrderStatus;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor

@Table(name = "order_item", indexes = {
        @Index(name="idx_order_item", columnList = "orderItemId")
})
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Order order;
    private OrderStatus status;
    private Long totalCnt;
    private Long totalPrice;
    private Long singlePrice;
    private String pname;
    private String poptionName;
    private Long productId;
    private Long productOptionId;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public OrderItem(Order order, OrderStatus status, Long totalCnt, Long totalPrice, Long singlePrice, String pname, String poptionName, Long productId, Long productOptionId, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.order = order;
        this.status = status;
        this.totalCnt = totalCnt;
        this.totalPrice = totalPrice;
        this.singlePrice = singlePrice;
        this.pname = pname;
        this.poptionName = poptionName;
        this.productId = productId;
        this.productOptionId = productOptionId;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Long getTotalCnt() {
        return totalCnt;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public Long getSinglePrice() {
        return singlePrice;
    }

    public String getPname() {
        return pname;
    }

    public String getPoptionName() {
        return poptionName;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getProductOptionId() {
        return productOptionId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
