package hh.plus.server.payment.domain.repository;

import hh.plus.server.payment.domain.PaymentStatus;
import hh.plus.server.payment.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {

    Payment save(Payment payment);

    Optional<Payment> findById(Long paymentId);

    @Query("SELECT p FROM Payment p WHERE p.paymentId = :paymentId AND p.status = :status")
    Optional<Payment> findByPaymentIdAndStatus(@Param("paymentId") Long paymentId, @Param("status") PaymentStatus status);
}
