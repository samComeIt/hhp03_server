package hh.plus.server.product.service;

import hh.plus.server.product.domain.entity.ProductOption;

import java.util.Optional;

public interface ProductOptionRepository {
    Optional<ProductOption> findById(Long optionId);
}
