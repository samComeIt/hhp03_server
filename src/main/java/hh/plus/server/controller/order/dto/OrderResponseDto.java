package hh.plus.server.controller.order.dto;

import hh.plus.server.controller.product.dto.ProductResponseDto;

import java.util.List;

public record OrderResponseDto(
        Long orderId,
        List<Long> orderDetailId

) {
    public static OrderResponseDto response(Long orderId, List<Long> orderDetailId)
    {
        return new OrderResponseDto(orderId, orderDetailId);
    }
}
