package hh.plus.server.orderSheet.domain.repository;

import hh.plus.server.orderSheet.controller.dto.OrderSheetDto;
import hh.plus.server.orderSheet.domain.entity.OrderSheet;

import java.util.Optional;

public interface OrderSheetRepository {
    OrderSheet save(OrderSheet orderSheet);

    Optional<OrderSheet> findById(Long orderSheetId);

    void delete(OrderSheet orderSheet);
}
