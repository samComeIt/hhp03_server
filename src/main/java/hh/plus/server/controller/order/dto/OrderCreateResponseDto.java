package hh.plus.server.controller.order.dto;

import java.util.List;

public record OrderCreateResponseDto(
        Long orderId,
        List<Long> orderDetailId

) {
    public static OrderCreateResponseDto response(Long orderId,  List<Long> orderDetailId)
    {
        return new OrderCreateResponseDto(orderId, orderDetailId);
    }
}