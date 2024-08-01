package hh.plus.server.product.cache;

import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.domain.repository.ProductRepository;
import hh.plus.server.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private CacheManager cacheManager;

    @Test
    @DisplayName("상품 조회 spring cache 테스트")
    public void testGetProductByIdCaching() throws InterruptedException {
        Long productId = 1L;
        Product product = Product.builder()
                .productId(productId)
                .name("Sample Main Product")
                .createdAt(LocalDateTime.now())
                .build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        int threadCount = 50;
        CountDownLatch latch = new CountDownLatch(threadCount);
        Long startTime = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    productService.getProductById(productId);
                } finally {
                    latch.countDown();
                }
            }).start();
        }

        latch.await(threadCount, TimeUnit.SECONDS); // Wait for all threads to complete

        long endTime = System.currentTimeMillis();

        log.info("Run time: {}", (endTime - startTime) + "ms");
    }
}
