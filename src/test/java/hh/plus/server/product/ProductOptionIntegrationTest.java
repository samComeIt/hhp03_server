package hh.plus.server.product;

import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.domain.repository.ProductOptionRepository;
import hh.plus.server.product.domain.repository.ProductRepository;
import hh.plus.server.product.service.ProductOptionService;
import hh.plus.server.product.service.ProductService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductOptionIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ProductService productService;
    @InjectMocks
    private ProductOptionService productOptionService;

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    @DisplayName("주문 완료로 상품 재고 업데이트 처리 실패 테스트")
    void testUpdateProductStockFail() throws Exception
    {
        Long productId = 100L;
        Long productOptionId = 10L;

        Optional<Product> optionalProduct= productRepository.findById(productId);
        Product mockProduct = optionalProduct.get();

        //given
        ProductOption productOption = new ProductOption(productOptionId, mockProduct, "상품옵션1", "ACTIVE", 101L, 5L, "false", LocalDateTime.now(), LocalDateTime.now());

        //when
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> {
                    productOptionService.updateStockById(productOptionId, 3L);
                }),
                CompletableFuture.runAsync(() -> {
                    productOptionService.updateStockById(productOptionId, 2L);
                }),
                CompletableFuture.runAsync(() -> {
                    productOptionService.updateStockById(productOptionId, 5L);
                })
        ).join();

        // then
        Optional<ProductOption> optionalProductResult = productOptionRepository.findById(productOptionId);
        assertTrue(optionalProductResult.get().getStock() >= 0);
    }
}
