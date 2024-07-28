package hh.plus.server.payment.domain.repository;

import hh.plus.server.payment.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {

    Payment save(Payment payment);

    Optional<Payment> findById(Long paymentId);
}
