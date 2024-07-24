package hh.plus.server.product.domain.repository.impl;

import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.domain.repository.ProductOptionJpaRepository;
import hh.plus.server.product.domain.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductOptionRepositoryImpl implements ProductOptionRepository {
    private final ProductOptionJpaRepository productOptionJpaRepository;

    @Override
    public Optional<ProductOption> findByIdWithPessimisticWriteLock(Long optionId) {
        return productOptionJpaRepository.findByIdWithPessimisticWriteLock(optionId);
    }

    @Override
    public Optional<ProductOption> findById(Long productOptionId) {
        return productOptionJpaRepository.findById(productOptionId);}

    @Override
    public ProductOption save(ProductOption productOption) { return productOptionJpaRepository.save(productOption); }
}
