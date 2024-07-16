package hh.plus.server.order.domain.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "order_sheet", indexes = {
        @Index(name="idx_order_sheet", columnList = "orderSheetId")
})
public class OrderSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderSheetId;

    @OneToMany(mappedBy = "orderSheet", cascade = CascadeType.ALL)
    private List<OrderSheetItem> orderSheetItem = new ArrayList<>();

    private LocalDateTime createdAt;

    public OrderSheet(List<OrderSheetItem> orderSheetItem, LocalDateTime createdAt) {
        this.orderSheetItem = orderSheetItem;
        this.createdAt = createdAt;
    }

    public Long getOrderSheetId() {
        return orderSheetId;
    }

    public void setOrderSheetId(Long orderSheetId) {
        this.orderSheetId = orderSheetId;
    }

    public List<OrderSheetItem> getOrderSheetItem() {
        return orderSheetItem;
    }

    public void setOrderSheetItem(List<OrderSheetItem> orderSheetItem) {
        this.orderSheetItem = orderSheetItem;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
