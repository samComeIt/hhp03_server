package hh.plus.server.order.controller.dto;


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
