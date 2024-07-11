package hh.plus.server.payment.service;

import hh.plus.server.order.domain.entity.Order;
import hh.plus.server.order.service.OrderRepository;
import hh.plus.server.payment.controller.dto.PaymentDto;
import hh.plus.server.payment.domain.entity.Payment;
import hh.plus.server.payment.domain.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    private final OrderRepository orderRepository;

    @Transactional
    public PaymentDto addPayment(PaymentDto paymentDto)
    {
        Payment payment = new Payment();

        payment.setOrderId(paymentDto.getOrderId());
        payment.setType(payment.getType());
        payment.setMethodType(payment.getMethodType());
        payment.setStatus(paymentDto.getStatus());
        payment.setUpdatedAt(paymentDto.getUpdatedAt());
        payment.setCreatedAt(paymentDto.getCreatedAt());

        paymentRepository.save(payment);
        return new PaymentDto();
    }

    @Transactional
    public PaymentDto updatePayment(Long paymentId, String status)
    {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);

        if (optionalPayment.isEmpty()) throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Payment does not exist"
        );

        Payment payment = optionalPayment.get();
        payment.setStatus(status);

        paymentRepository.save(payment);
        return new PaymentDto();
    }
}
