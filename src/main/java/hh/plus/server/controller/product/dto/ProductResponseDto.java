package hh.plus.server.controller.product.dto;

public record ProductResponseDto(
        Long productId
){
    public static ProductResponseDto response(Long productId)
    {
        return new ProductResponseDto(productId);
    }
}
