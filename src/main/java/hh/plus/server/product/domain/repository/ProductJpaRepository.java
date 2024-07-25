package hh.plus.server.product.domain.repository;

import hh.plus.server.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductJpaRepository extends CrudRepository<Product, Long> {
    Optional<Product> findById(Long productId);
}
