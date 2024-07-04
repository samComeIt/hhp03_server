package hh.plus.server.controller.product.dto;

import java.util.HashMap;
import java.util.List;

public record ProductOrderResponseDto(
        List<Long> productIds
) {
        public static ProductOrderResponseDto response(List<Long> productIds)
        {
            return new ProductOrderResponseDto(productIds);
        }
}
