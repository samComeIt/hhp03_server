package hh.plus.server.controller.product.dto;

import java.util.ArrayList;
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
