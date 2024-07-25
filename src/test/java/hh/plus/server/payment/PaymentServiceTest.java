package hh.plus.server.payment;

import hh.plus.server.payment.domain.repository.PaymentRepository;
import hh.plus.server.payment.service.PaymentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

}
