package hh.plus.server.product.service.dto;

import java.util.List;

public record ProductOrderResponseDto(
        List<Long> productIds
) {
        public static ProductOrderResponseDto response(List<Long> productIds)
        {
            return new ProductOrderResponseDto(productIds);
        }
}
