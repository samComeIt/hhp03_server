package hh.plus.server.order.controller.dto;

import hh.plus.server.order.config.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderCreateResponseDto(
        Long orderId,
        OrderStatus status,
        List<OrderDetailResponseDto> orderDetail,
        LocalDateTime updatedAt,
        LocalDateTime createdAt


) {
    public static OrderCreateResponseDto response(Long orderId,  OrderStatus status, List<OrderDetailResponseDto> orderDetail, LocalDateTime updatedAt, LocalDateTime createdAt)
    {
        return new OrderCreateResponseDto(orderId, status, orderDetail, updatedAt, createdAt);
    }
}
