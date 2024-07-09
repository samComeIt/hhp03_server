package hh.plus.server.product.infrastructure;

import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.service.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductOptionRepositoryImpl implements ProductOptionRepository {
    private final ProductOptionJpaRepository productOptionJpaRepository;

    @Override
    public Optional<ProductOption> findById(Long productOptionId) {
        return productOptionJpaRepository.findById(productOptionId);}
}
