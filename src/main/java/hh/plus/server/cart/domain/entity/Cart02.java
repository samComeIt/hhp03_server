package hh.plus.server.cart.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Cart02 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    private Long userId;

    private Long productId;

    private Long productOptionId;

    private String productName;

    private String productOptionName;

    private Long singlePrice;

    private Long totalPrice;

    private Long quantity;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    @Builder
    public Cart02(Long userId, Long productId, Long productOptionId, String productName, String productOptionName, Long singlePrice, Long totalPrice, Long quantity, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.userId = userId;
        this.productId = productId;
        this.productOptionId = productOptionId;
        this.productName = productName;
        this.productOptionName = productOptionName;
        this.singlePrice = singlePrice;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getProductOptionId() {
        return productOptionId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductOptionName() {
        return productOptionName;
    }

    public Long getSinglePrice() {
        return singlePrice;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
