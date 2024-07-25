package hh.plus.server.cart.domain.entity;

import hh.plus.server.order.domain.entity.Order;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    private Long productId;

    private Long productOptionId;

    private String productName;

    private String productOptionName;

    private Long singlePrice;

    private Long totalPrice;

    private Long quantity;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public CartItem(Cart cart, Long productId, Long productOptionId, String productName, String productOptionName, Long singlePrice, Long totalPrice, Long quantity, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.cart = cart;
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

    public Long getCartItemId() {
        return cartItemId;
    }

    public Cart getCart() {
        return cart;
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
