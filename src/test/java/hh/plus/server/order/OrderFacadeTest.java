package hh.plus.server.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.domain.repository.BalanceRepository;
import hh.plus.server.balance.service.BalanceService;
import hh.plus.server.balance.service.dto.BalanceResponseDto;
import hh.plus.server.order.domain.entity.Order;
import hh.plus.server.order.domain.repository.OrderRepository;
import hh.plus.server.order.service.dto.request.OrderSheetCreateRequestDto;
import hh.plus.server.payment.domain.entity.Payment;
import hh.plus.server.product.domain.Status;
import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.domain.repository.ProductOptionRepository;
import hh.plus.server.product.service.ProductService;
import jakarta.annotation.security.RunAs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class OrderFacadeTest {

    @Mock
    private ProductOptionRepository productOptionRepository;

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceService balanceService;

    @InjectMocks
    private ProductService productService;

    public OrderFacadeTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("상품 재고 차감 성공 후 잔액 차감 동시성 제어 테스트")
    public void testConcurrentProductStockAndBalanceUpdate() throws InterruptedException {
        long balanceId = 100L;
        Balance balance = Balance.builder()
                .balanceId(balanceId)
                .balance(20000L)
                .build();

        long productOptionId = 201L;
        long stock = 30L;
        long price = 1000L;
        ProductOption productOption = ProductOption.builder()
                .name("옵션 10")
                .status(Status.ACTIVE)
                .stock(stock)
                .price(price)
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        when(balanceRepository.findByIdWithPessimisticWriteLock(balanceId)).thenReturn(Optional.of(balance));
        when(productOptionRepository.findByIdWithPessimisticWriteLock(productOptionId)).thenReturn(Optional.of(productOption));

        int threadCount = 10;
        CountDownLatch latch = new CountDownLatch(threadCount);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    latch.await();
                    productService.updateStockById(productOptionId, 1L);
                    balanceService.updateBalance(balanceId, -price);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            latch.countDown();
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        ProductOption finalProductOption = productOptionRepository.findByIdWithPessimisticWriteLock(productOptionId).orElseThrow();
        Balance finalBalance = balanceRepository.findByIdWithPessimisticWriteLock(balanceId).orElseThrow();

        assertEquals(20, finalProductOption.getStock()); // Assuming each thread decreases stock by 1
        assertEquals(10000, finalBalance.getBalance()); // Assuming each thread decreases balance by 1000

        verify(productOptionRepository, times(threadCount+1)).findByIdWithPessimisticWriteLock(productOptionId);
        verify(productOptionRepository, times(threadCount)).save(any(ProductOption.class));
        verify(balanceRepository, times(threadCount+1)).findByIdWithPessimisticWriteLock(balanceId);
        verify(balanceRepository, times(threadCount)).save(any(Balance.class));
    }
}
