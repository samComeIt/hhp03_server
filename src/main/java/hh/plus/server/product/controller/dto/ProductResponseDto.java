package hh.plus.server.product.controller.dto;

import java.util.List;

public record ProductResponseDto(
        Long productId,
        String productName,
        List<Long> optionIds
){
    public static ProductResponseDto response(Long productId, String productName, List<Long> optionIds)
    {
        return new ProductResponseDto(productId, productName, optionIds);
    }
}
