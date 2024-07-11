package hh.plus.server.order.domain.impl;

import hh.plus.server.order.domain.entity.Order;
import hh.plus.server.order.domain.repository.OrderDetailJpaRepository;
import hh.plus.server.order.domain.repository.OrderJpaRepository;
import hh.plus.server.order.service.OrderDetailRepository;
import hh.plus.server.product.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderDetailRepositoryImpl implements OrderDetailRepository {
    private final OrderDetailJpaRepository orderDetailJpaRepository;

    @Override
    public List<Product> findTopSoldProduct(LocalDate startDate, LocalDate endDate){
        return orderDetailJpaRepository.findTopSoldProduct(startDate, endDate);}
}
