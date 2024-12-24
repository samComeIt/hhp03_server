package hh.plus.server.order.domain.repository;

import hh.plus.server.order.domain.entity.OrderSheet;

import java.util.Optional;

public interface OrderSheetRepository {
    OrderSheet save(OrderSheet orderSheet);

    Optional<OrderSheet> findById(Long orderSheetId);

    void delete(OrderSheet orderSheet);
}
