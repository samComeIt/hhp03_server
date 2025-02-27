package hh.plus.server.product.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hh.plus.server.product.domain.Status;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
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
    private Status status;

    @Column(name = "order_cnt")
    private Long orderCnt;
    private Long stock;
    private Long price;

    @Column(name = "is_deleted")
    private String isDeleted;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public ProductOption(Product product, String name, Status status, Long orderCnt, Long stock, Long price, String isDeleted, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.product = product;
        this.name = name;
        this.status = status;
        this.orderCnt = orderCnt;
        this.stock = stock;
        this.price = price;
        this.isDeleted = isDeleted;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Long getProductOptionId() {
        return productOptionId;
    }

    public Product getProduct() {
        return product;
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

    public Long getStock() {
        return stock;
    }

    public Long getPrice() {
        return price;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
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

    public void isStockAvailable(Long quantity)
    {
        if (this.stock < quantity) throw new RuntimeException("Not enough stock");
    }
}
