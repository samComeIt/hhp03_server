package hh.plus.server.order.domain.listener;

public class OrderCreatedEvent {
    private final Long orderId;

    public OrderCreatedEvent(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
