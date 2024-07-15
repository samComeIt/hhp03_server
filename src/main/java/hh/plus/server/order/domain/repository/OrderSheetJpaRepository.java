package hh.plus.server.order.domain.repository;

import hh.plus.server.order.domain.entity.OrderSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderSheetJpaRepository extends JpaRepository<OrderSheet, Long> {
    OrderSheet save(OrderSheet orderSheet);

    Optional<OrderSheet> findById(Long orderSheetId);

    void delete(OrderSheet orderSheet);
}
