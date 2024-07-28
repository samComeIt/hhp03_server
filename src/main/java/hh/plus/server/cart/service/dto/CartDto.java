package hh.plus.server.cart.service.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CartDto (
        Long cartId,
        List<CartItemDto> cartItem,
        LocalDateTime createdAt,
        LocalDateTime at){
}
