package hh.plus.server.product.controller.dto.productOption;

public record ProductOptionResponseDto (
        Long productOptionId,
        Long stock
) {
    public static ProductOptionResponseDto response(Long productOptionId, Long stock)
    {
        return new ProductOptionResponseDto(productOptionId, stock);
    }
}
