package hh.plus.server.product.infrastructure;

import hh.plus.server.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long productId);
}
