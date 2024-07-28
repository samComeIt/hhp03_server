package hh.plus.server.cart.service.dto;

import java.time.LocalDateTime;
import java.util.List;

public record Cart02Dto(
        Long cartId,
        Long productId,
        Long productOptionId,
        String productName,
        String productOptionName,
        Long singlePrice,
        Long totalPrice,
        Long quantity){
}
