package hh.plus.server.controller.product.dto;

public record ProductDetailResponseDto(
        Long productId,
        Long productOptionId
){
    public static ProductDetailResponseDto response(Long productId, Long productOptionId)
    {
        return new ProductDetailResponseDto(productId, productOptionId);
    }
}
