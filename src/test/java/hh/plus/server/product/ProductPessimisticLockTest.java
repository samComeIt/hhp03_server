package hh.plus.server.product;

import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.domain.repository.BalanceRepository;
import hh.plus.server.balance.service.BalanceService;
import hh.plus.server.product.domain.Status;
import hh.plus.server.product.domain.entity.Product;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductPessimisticLockTest {

    @Mock
    private ProductOptionRepository productOptionRepository;

    @InjectMocks
    private ProductService productService;

    public ProductPessimisticLockTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("재고 차감 동시성 제어 테스트")
    public void testDeductProductStockPessimisticLock() throws InterruptedException {
        long productOptionId = 201L;

        ProductOption productOption = ProductOption.builder()
                .name("옵션 10")
                .status(Status.ACTIVE)
                .stock(30L)
                .price(1000L)
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        when(productOptionRepository.findByIdWithPessimisticWriteLock(productOptionId)).thenReturn(Optional.of(productOption));

        for (int i = 0; i < 10; i++) {
            runConcurrentChargeOperations(productOptionId);
        }

        Thread.sleep(500);

        ProductOption finalProductOption = productOptionRepository.findByIdWithPessimisticWriteLock(productOptionId).orElseThrow();

        assertEquals(20, finalProductOption.getStock());

        verify(productOptionRepository, times(11)).findByIdWithPessimisticWriteLock(productOptionId);
        verify(productOptionRepository, times(10)).save(any(ProductOption.class));

    }

    private void runConcurrentChargeOperations(long productOptionId) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            try {
                latch.await();
                productService.updateStockById(productOptionId, 1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        latch.countDown();

        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }
}
