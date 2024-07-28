package hh.plus.server.cart.service.dto;

public record CartCreateRequestDto (
        Long userId,
        Long productId,
        Long productOptionId,
        Long quantity
)  {
}
