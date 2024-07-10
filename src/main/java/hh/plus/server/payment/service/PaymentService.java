package hh.plus.server.payment.service;

import hh.plus.server.order.domain.entity.Order;
import hh.plus.server.order.service.OrderRepository;
import hh.plus.server.payment.controller.dto.PaymentDto;
import hh.plus.server.payment.domain.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    private final OrderRepository orderRepository;

    public PaymentDto addPayment(PaymentDto paymentDto)
    {
        Payment payment = new Payment();
        Optional<Order> purchaseOrder = orderRepository.findById(paymentDto.getOrderId());

        payment.setOrder(purchaseOrder.get());

        paymentRepository.save(payment);
        return new PaymentDto();
    }

    public PaymentDto updatePayment(Long paymentId, String status)
    {
        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        payment.setStatus(status);

        Optional<Order> purchaseOrder = orderRepository.findById(paymentId);

        payment.setOrder(purchaseOrder.get());

        paymentRepository.save(payment);
        return new PaymentDto();
    }
}
