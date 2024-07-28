package hh.plus.server.product.service.dto;

import java.util.List;
import java.util.Optional;

public record ProductDto(
      Long productId,
      String productName,
      List<ProductOptionDto> productOptionDto
)  {
}
