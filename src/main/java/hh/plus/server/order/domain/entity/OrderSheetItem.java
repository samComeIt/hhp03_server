package hh.plus.server.order.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hh.plus.server.order.domain.OrderStatus;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "order_sheet_item", indexes = {
        @Index(name="idx_order_sheet_item", columnList = "orderSheetItemId")
})
public class OrderSheetItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderSheetItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_sheet_id", nullable = false)
    @JsonBackReference
    private OrderSheet orderSheet;

    private Long cartId;

    private OrderStatus status;
    private Long totalCnt;
    private Long totalPrice;
    private Long singlePrice;
    private String pname;
    private String poptionName;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    private Long productId;
    private Long productOptionId;

    public OrderSheetItem(OrderSheet orderSheet, Long cartId, OrderStatus status, Long totalCnt, Long totalPrice, Long singlePrice, String pname, String poptionName, LocalDateTime updatedAt, LocalDateTime createdAt, Long productId, Long productOptionId) {
        this.orderSheet = orderSheet;
        this.cartId = cartId;
        this.status = status;
        this.totalCnt = totalCnt;
        this.totalPrice = totalPrice;
        this.singlePrice = singlePrice;
        this.pname = pname;
        this.poptionName = poptionName;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.productId = productId;
        this.productOptionId = productOptionId;
    }

    public Long getOrderSheetItemId() {
        return orderSheetItemId;
    }

    public OrderSheet getOrderSheet() {
        return orderSheet;
    }

    public Long getCartId() {
        return cartId;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getProductOptionId() {
        return productOptionId;
    }
}
