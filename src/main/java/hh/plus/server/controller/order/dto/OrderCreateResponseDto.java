package hh.plus.server.controller.order.dto;

public record OrderCreateResponseDto(
        OrderCreateRequestDto orderCreateRequestDto
) {
    public static Long response(OrderCreateRequestDto orderCreateRequestDto)
    {
        return 100L;
    }
}
