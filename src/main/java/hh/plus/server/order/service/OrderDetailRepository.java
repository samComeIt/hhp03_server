package hh.plus.server.order.service;

import hh.plus.server.product.domain.entity.Product;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface OrderDetailRepository {
    List<Product> findTopSoldProduct(LocalDate startDate, LocalDate endDate);
}
