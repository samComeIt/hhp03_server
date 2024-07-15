package hh.plus.server.payment.service;

import hh.plus.server.payment.config.PaymentStatus;
import hh.plus.server.payment.controller.dto.PaymentDto;
import hh.plus.server.payment.domain.entity.Payment;
import hh.plus.server.payment.domain.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Transactional
    public PaymentDto addPayment(PaymentDto paymentDto)
    {
        Payment payment = new Payment(
                paymentDto.getOrderId()
                , paymentDto.getType()
                , paymentDto.getMethod_type()
                , PaymentStatus.PENDING
                , paymentDto.getAmount()
                , paymentDto.getUpdatedAt()
                , LocalDateTime.now()
        );

        paymentRepository.save(payment);
        return new PaymentDto();
    }

    @Transactional
    public void updatePayment(Long paymentId, PaymentStatus status)
    {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        payment.updateStatus(status);
        paymentRepository.save(payment);
    }
}
