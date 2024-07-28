package hh.plus.server.cart.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import hh.plus.server.product.domain.entity.ProductOption;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CartItem> cartItem = new ArrayList<>();

    private Long userId;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Cart(List<CartItem> cartItem, Long userId, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.cartItem = cartItem;
        this.userId = userId;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Long getCartId() {
        return cartId;
    }

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
