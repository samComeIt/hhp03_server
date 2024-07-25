package hh.plus.server.product.domain.repository;

import hh.plus.server.product.domain.entity.ProductOption;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface ProductOptionJpaRepository extends CrudRepository<ProductOption, Long> {
    Optional<ProductOption> findById(Long productOptionId);

    ProductOption save(ProductOption productOption);
}
