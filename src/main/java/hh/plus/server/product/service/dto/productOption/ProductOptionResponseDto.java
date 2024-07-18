package hh.plus.server.product.service.dto.productOption;

public record ProductOptionResponseDto (
        Long productOptionId,
        Long stock
) {
    public static ProductOptionResponseDto response(Long productOptionId, Long stock)
    {
        return new ProductOptionResponseDto(productOptionId, stock);
    }
}
