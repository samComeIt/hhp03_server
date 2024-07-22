package hh.plus.server.order.service.dto.request;

public record OrderItemCreateRequestDto (
        Long cartId,
        Long productId,
        Long productOptionId,
        String productName,
        String productOptionName,
        Long quantity,
        Long singlePrice,
        Long totalPrice

){
}
