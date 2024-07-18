package hh.plus.server.product.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "product_option", indexes = {
        @Index(name="idx_product_option", columnList = "productOptionId"),
        @Index(name="idx_pid", columnList = "productId"),
        @Index(name="idx_pstatus", columnList = "status"),
        @Index(name="idx_stock", columnList = "stock"),
        @Index(name="idx_p_is_deleted", columnList = "isDeleted"),
})
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_option_id")
    private Long productOptionId;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;

    private String name;
    private String status;

    @Column(name = "order_cnt")
    private Long orderCnt;
    private Long stock;

    @Column(name = "is_deleted")
    private String isDeleted;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public ProductOption(Long productOptionId, Product product, String name, String status, Long orderCnt, Long stock, String isDeleted, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.productOptionId = productOptionId;
        this.product = product;
        this.name = name;
        this.status = status;
        this.orderCnt = orderCnt;
        this.stock = stock;
        this.isDeleted = isDeleted;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Long getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(Long productOptionId) {
        this.productOptionId = productOptionId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
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

    public void minusStock(Long stock)
    {
        if(this.stock - stock < 0) throw new RuntimeException("Cannot be minus");

        this.stock -= stock;
    }

    public void plusStock(Long stock)
    {
        if(this.stock + stock < 0) throw new RuntimeException("Cannot be minus");

        this.stock += stock;
    }
}
