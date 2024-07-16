package hh.plus.server.order.domain.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

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
    private OrderSheet orderSheet;

    private String status;
    private Long totalCnt;
    private Long totalPrice;
    private Long singlePrice;
    private String pname;
    private String poptionName;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    private Long productId;
    private Long productOptionId;

    public OrderSheetItem(OrderSheet orderSheet, String status, Long totalCnt, Long totalPrice, Long singlePrice, String pname, String poptionName, LocalDateTime updatedAt, LocalDateTime createdAt, Long productId, Long productOptionId) {
        this.orderSheet = orderSheet;
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

    public void setOrderSheetItemId(Long orderSheetItemId) {
        this.orderSheetItemId = orderSheetItemId;
    }

    public OrderSheet getOrderSheet() {
        return orderSheet;
    }

    public void setOrderSheet(OrderSheet orderSheet) {
        this.orderSheet = orderSheet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(Long totalCnt) {
        this.totalCnt = totalCnt;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(Long singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPoptionName() {
        return poptionName;
    }

    public void setPoptionName(String poptionName) {
        this.poptionName = poptionName;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(Long productOptionId) {
        this.productOptionId = productOptionId;
    }
}
