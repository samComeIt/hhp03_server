package hh.plus.server.product.service;

import hh.plus.server.product.domain.entity.Product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(Long productId);

}
