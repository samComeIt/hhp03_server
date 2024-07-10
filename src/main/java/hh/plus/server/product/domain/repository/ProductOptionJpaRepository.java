package hh.plus.server.product.domain.repository;

import hh.plus.server.product.domain.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductOptionJpaRepository extends JpaRepository<ProductOption, Long> {
    Optional<ProductOption> findById(Long productOptionId);
}
