package hh.plus.server.payment.domain.repository;

import hh.plus.server.payment.domain.PaymentStatus;
import hh.plus.server.payment.domain.entity.Payment;

import java.util.Optional;

public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> findById(Long productId);

    Optional<Payment> findByPaymentIdAndStatus(Long paymentId, PaymentStatus status);
}
