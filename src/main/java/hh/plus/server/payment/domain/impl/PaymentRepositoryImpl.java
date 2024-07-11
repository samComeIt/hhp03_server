package hh.plus.server.payment.domain.impl;

import hh.plus.server.payment.domain.entity.Payment;
import hh.plus.server.payment.domain.repository.PaymentJpaRepository;
import hh.plus.server.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Payment save(Payment payment) { return paymentJpaRepository.save(payment); }

    @Override
    public Optional<Payment> findById(Long paymentId) { return paymentJpaRepository.findById(paymentId); }
}
