package hh.plus.server.order.domain.entity;


import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.domain.entity.ProductOption;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor

@Table(name = "order_detail", indexes = {
        @Index(name="idx_order_detail", columnList = "orderDetailId")
})
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;

    private String status;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private Long productId;
    private Long productOptionId;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
