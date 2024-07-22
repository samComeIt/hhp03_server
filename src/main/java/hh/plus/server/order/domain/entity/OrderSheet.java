package hh.plus.server.order.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "orderSheet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderSheetItem> orderSheetItem = new ArrayList<>();

    private LocalDateTime createdAt;

    public OrderSheet(List<OrderSheetItem> orderSheetItem, LocalDateTime createdAt) {
        this.orderSheetItem = orderSheetItem;
        this.createdAt = createdAt;
    }

    public Long getOrderSheetId() {
        return orderSheetId;
    }

    public List<OrderSheetItem> getOrderSheetItem() {
        return orderSheetItem;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void addItem(OrderSheetItem item)
    {
        orderSheetItem.add(item);
    }
}
