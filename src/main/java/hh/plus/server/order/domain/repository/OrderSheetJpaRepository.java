package hh.plus.server.order.domain.repository;

import hh.plus.server.order.domain.entity.OrderSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderSheetJpaRepository extends CrudRepository<OrderSheet, Long> {
    OrderSheet save(OrderSheet orderSheet);

    Optional<OrderSheet> findById(Long orderSheetId);

    void delete(OrderSheet orderSheet);
}
