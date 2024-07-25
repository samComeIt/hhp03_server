package hh.plus.server.product;

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
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductOptimisticLockTest {

    @Mock
    private ProductOptionRepository productOptionRepository;

    public ProductOptimisticLockTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("재고 차감 동시성 제어 테스트(Optimistic Locking)")
    public void testDeductProductStockOptimisticLock() throws InterruptedException {
        Logger logger = Logger.getLogger(getClass().getName());

        long productOptionId = 201L;

        ProductOption productOption = ProductOption.builder()
                .name("옵션 10")
                .status(Status.ACTIVE)
                .stock(30L)
                .price(1000L)
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        when(productOptionRepository.findById(productOptionId)).thenReturn(Optional.of(productOption));

        for (int i = 0; i < 10; i++) {
            runConcurrentChargeOperations(productOptionId, logger);
        }

        Thread.sleep(500);

        ProductOption finalProductOption = productOptionRepository.findById(productOptionId).orElseThrow();

        assertEquals(20, finalProductOption.getStock());

        verify(productOptionRepository, times(11)).findById(productOptionId);
        verify(productOptionRepository, times(10)).save(any(ProductOption.class));

    }

    private void runConcurrentChargeOperations(long productOptionId, Logger logger) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            try {
                logger.info("Thread started: " + Thread.currentThread().getName());
                ProductOption productOption = productOptionRepository.findById(productOptionId).orElseThrow();

                logger.info("Current stock before update in thread " + Thread.currentThread().getName() + ": " + productOption.getStock());
                productOption.minusStock(1L);

                productOptionRepository.save(productOption);
                logger.info("Updated stock in thread " + Thread.currentThread().getName() + ": " + productOption.getStock());
            } catch (Exception e) {
                logger.severe("Exception occurred in thread " + Thread.currentThread().getName() + ": " + e.getMessage());
            }
        });

        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }
}
