package hh.plus.server.product.domain.repository;

import hh.plus.server.product.domain.entity.ProductOption;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface ProductOptionRepository {
    Optional<ProductOption> findByIdWithPessimisticWriteLock(Long optionId);
    Optional<ProductOption> findById(Long optionId);

    ProductOption save(ProductOption productOption);
}
