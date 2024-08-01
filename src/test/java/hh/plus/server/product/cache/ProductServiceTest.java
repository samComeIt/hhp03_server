package hh.plus.server.product.cache;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import hh.plus.server.common.config.RedisConfig;
import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.domain.repository.ProductRepository;
import hh.plus.server.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.Cache;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private RedisConfig cacheManager;

    @Test
    @DisplayName("상품 조회 redis cache 테스트")
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
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        Long startTime = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    latch.await();
                    // First call, should hit the repository
                    Product result1 = productService.getProductById(productId);

                    // Second call, should hit the cache
                    Product result2 = productService.getProductById(productId);

                    // Third call, should hit the cache
                    Product result3 = productService.getProductById(productId);

                    // Fourth call
                    Product result4 = productService.getProductById(productId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            latch.countDown();
        }

        executor.shutdown();

        Long endTime = System.currentTimeMillis();

        log.info("Run time: {}", (endTime - startTime) + "ms");
    }
}
