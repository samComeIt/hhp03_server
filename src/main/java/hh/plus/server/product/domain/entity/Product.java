package hh.plus.server.product.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import hh.plus.server.product.domain.Status;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "product", indexes = {
        @Index(name="idx_product", columnList = "productId"),
        @Index(name="idx_status", columnList = "status"),
        @Index(name="idx_is_deleted", columnList = "isDeleted"),
})
public class Product implements Serializable {
    private static final long serialVersionUID = 6494678977089006639L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    private String name;

    private Status status;

    @Column(name = "order_cnt")
    private Long orderCnt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProductOption> productOption = new ArrayList<>();

    @Builder
    public Product(Long productId, String name, LocalDateTime createdAt) {
        this.productId = productId;
        this.name = name;
        this.createdAt = createdAt;
    }

    public Product(String name, Status status, Long orderCnt, Boolean isDeleted, LocalDateTime createdAt, List<ProductOption> productOption) {
        this.name = name;
        this.status = status;
        this.orderCnt = orderCnt;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.productOption = productOption;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public Long getOrderCnt() {
        return orderCnt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<ProductOption> getProductOption() {
        return productOption;
    }
}
