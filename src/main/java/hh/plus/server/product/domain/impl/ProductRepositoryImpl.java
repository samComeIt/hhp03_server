package hh.plus.server.product.domain.impl;

import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.domain.repository.ProductJpaRepository;
import hh.plus.server.product.service.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository productJpaRepository;

    @Override
    public Optional<Product> findById(Long productId) { return productJpaRepository.findById(productId);}
}
