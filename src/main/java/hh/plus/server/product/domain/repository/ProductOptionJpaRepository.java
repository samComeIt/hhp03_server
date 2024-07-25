package hh.plus.server.product.domain.repository;

import hh.plus.server.product.domain.entity.ProductOption;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductOptionJpaRepository extends JpaRepository<ProductOption, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM Product_Option po WHERE po.product_option_id = :optionId")
    Optional<ProductOption> findByIdWithPessimisticWriteLock(Long optionId);
    Optional<ProductOption> findById(Long productOptionId);

    ProductOption save(ProductOption productOption);
}
