package hh.plus.server.orderSheet.domain.impl;

import hh.plus.server.orderSheet.controller.dto.OrderSheetDto;
import hh.plus.server.orderSheet.domain.entity.OrderSheet;
import hh.plus.server.orderSheet.domain.repository.OrderSheetJpaRepository;
import hh.plus.server.orderSheet.domain.repository.OrderSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class OrderSheetRepositoryImpl implements OrderSheetRepository {
    private final OrderSheetJpaRepository orderSheetJpaRepository;

    @Override
    public OrderSheet save(OrderSheet orderSheet) { return orderSheetJpaRepository.save(orderSheet);}

    @Override
    public Optional<OrderSheet> findById(Long orderSheetId) { return orderSheetJpaRepository.findById(orderSheetId); }

    @Override
    public void delete(OrderSheet orderSheet) { orderSheetJpaRepository.delete(orderSheet);}
}
