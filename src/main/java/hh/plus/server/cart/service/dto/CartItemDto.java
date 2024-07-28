package hh.plus.server.cart.service.dto;

import java.time.LocalDateTime;

public record CartItemDto(
        Long cartItemId,
        Long cartId,
        Long productId,
        Long productOptionId,
        String productName,
        String productOptionName,
        Long singlePrice,
        Long totalPrice,
        Long quantity,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}
