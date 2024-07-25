package hh.plus.server.payment.domain.repository;

import hh.plus.server.payment.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PaymentJpaRepository extends CrudRepository<Payment, Long> {

    Payment save(Payment payment);

    Optional<Payment> findById(Long paymentId);
}
