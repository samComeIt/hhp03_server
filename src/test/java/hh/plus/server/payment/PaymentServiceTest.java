package hh.plus.server.payment;

import hh.plus.server.payment.domain.entity.Payment;
import hh.plus.server.payment.domain.repository.PaymentRepository;
import hh.plus.server.payment.service.PaymentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    @DisplayName("결제 정보 생성 실패 케이스")
    public void testCreatePaymentFail() {
        // given
//        Payment payment = new Payment();
//        Long orderId = 100L;
//
//        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
//        payment.setPaymentId(1L);
//        payment.setOrderId(orderId);
//        payment.setType(paymentRequestDto.getType());
//        payment.setMethodType(paymentRequestDto.getMethod_type());
//        payment.setStatus(paymentRequestDto.getStatus());
//        payment.setAmount(paymentRequestDto.getAmount());
//        payment.setUpdatedAt(paymentRequestDto.getUpdatedAt());
//        payment.setCreatedAt(paymentRequestDto.getCreatedAt());
//
//        PaymentDto paymentDto = new PaymentDto(1L, orderId, 1000L, "PAY", "CARD", "NOT_COMPLETE", LocalDateTime.now(), LocalDateTime.now());
//
//        PaymentDto mockPayment = paymentService.addPayment(paymentDto);
//        // when
//        when(paymentRepository.save(payment)).thenReturn(payment);
//
//
//        // then
//        assertEquals(payment, mockPayment);
    }
}
