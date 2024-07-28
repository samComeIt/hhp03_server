package hh.plus.server.product.service.dto;

import java.util.List;

public record ProductDetailResponseDto(
        Long productId,
        String productName,
        List<Long> optionIds
){
    public static ProductDetailResponseDto response(Long productId, String productName, List<Long> optionIds)
    {
        return new ProductDetailResponseDto(productId, productName, optionIds);
    }
}
