package hh.plus.server.order.domain.repository;

import hh.plus.server.product.domain.entity.Product;

import java.time.LocalDate;
import java.util.List;

public interface OrderDetailRepository {
    List<Product> findTopSoldProduct(LocalDate startDate, LocalDate endDate);
}
