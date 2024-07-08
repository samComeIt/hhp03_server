package hh.plus.server.product.domain.entity;

import hh.plus.server.product.domain.Status;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "product", indexes = {
        @Index(name="idx_product", columnList = "productId"),
        @Index(name="idx_status", columnList = "status"),
        @Index(name="idx_is_deleted", columnList = "isDeleted"),
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    private String name;
    private String status;

    @Column(name = "order_cnt")
    private Long orderCnt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductOption> productOption;

    public Product(Long productId, String name, String status, Long orderCnt, Boolean isDeleted, LocalDateTime createdAt, List<ProductOption> productOption) {
        this.productId = productId;
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

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOrderCnt() {
        return orderCnt;
    }

    public void setOrderCnt(Long orderCnt) {
        this.orderCnt = orderCnt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<ProductOption> getProductOption() {
        return productOption;
    }

    public void setProductOption(List<ProductOption> productOption) {
        this.productOption = productOption;
    }
}
