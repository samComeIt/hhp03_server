package hh.plus.server.product.domain.repository;

import hh.plus.server.product.domain.entity.ProductOption;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface ProductOptionJpaRepository extends JpaRepository<ProductOption, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM Product_Option po WHERE po.product_option_id = :optionId")
    Optional<ProductOption> findByIdWithPessimisticWriteLock(Long optionId);

    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
    Optional<ProductOption> findById(Long productOptionId);

    @Transactional
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    ProductOption save(ProductOption productOption);
}
