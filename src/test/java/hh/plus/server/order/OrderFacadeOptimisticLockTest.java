package hh.plus.server.order;

import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.domain.repository.BalanceRepository;
import hh.plus.server.balance.service.BalanceService;
import hh.plus.server.product.domain.Status;
import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.domain.repository.ProductOptionRepository;
import hh.plus.server.product.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class OrderFacadeOptimisticLockTest {

    @Mock
    private ProductOptionRepository productOptionRepository;

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceService balanceService;

    @InjectMocks
    private ProductService productService;

    public OrderFacadeOptimisticLockTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("상품 재고 차감 성공 후 잔액 차감 동시성 제어 테스트(Optimistic Locking)")
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

        when(balanceRepository.findById(balanceId)).thenReturn(Optional.of(balance));
        when(productOptionRepository.findById(productOptionId)).thenReturn(Optional.of(productOption));

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

        ProductOption finalProductOption = productOptionRepository.findById(productOptionId).orElseThrow();
        Balance finalBalance = balanceRepository.findById(balanceId).orElseThrow();

        assertEquals(20, finalProductOption.getStock()); // Assuming each thread decreases stock by 1
        assertEquals(10000, finalBalance.getBalance()); // Assuming each thread decreases balance by 1000

        verify(productOptionRepository, times(threadCount+1)).findById(productOptionId);
        verify(productOptionRepository, times(threadCount)).save(any(ProductOption.class));
        verify(balanceRepository, times(threadCount+1)).findById(balanceId);
        verify(balanceRepository, times(threadCount)).save(any(Balance.class));
    }
}
