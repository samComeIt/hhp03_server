package hh.plus.server.product.controller.dto;

import java.util.List;

public record ProductOrderResponseDto(
        List<Long> productIds
) {
        public static ProductOrderResponseDto response(List<Long> productIds)
        {
            return new ProductOrderResponseDto(productIds);
        }
}
