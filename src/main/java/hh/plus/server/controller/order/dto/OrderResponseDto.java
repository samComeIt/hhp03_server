package hh.plus.server.controller.order.dto;

import hh.plus.server.controller.product.dto.ProductResponseDto;

public record OrderResponseDto(
        Long orderId
) {
    public static OrderResponseDto response(Long orderId)
    {
        return new OrderResponseDto(orderId);
    }
}
