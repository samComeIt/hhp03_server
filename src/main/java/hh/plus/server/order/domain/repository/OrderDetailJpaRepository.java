package hh.plus.server.order.domain.repository;

import hh.plus.server.order.domain.entity.OrderItem;
import hh.plus.server.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderDetailJpaRepository extends CrudRepository<OrderItem, Long> {

    @Query(value = "SELECT od.product " +
            "FROM OrderDetail od" +
            "JOIN od.order o" +
            "WHERE o.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY od.pid" +
            "ORDER BY SUM(od.totalCnt) DESC", nativeQuery = true)
    List<Product> findTopSoldProduct(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
