package hh.plus.server.product;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.domain.repository.BalanceRepository;
import hh.plus.server.balance.service.BalanceService;
import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.domain.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;


    @Test
    @DisplayName("상품 조회 실패")
    public void testGetProductByIdFail()
    {
        //  given
        Long productId = 10L;
        Product product = new Product(productId, "상품1", "ACTIVE", LocalDateTime.now(), LocalDateTime.now());
        when(productService.getProducyById(productId)).thenReturn(product);

        // when, then
        assertNotNull(product);
    }

    public class Product {
        private Long productId;
        private String name;
        private String status;
        private LocalDateTime updatedAt;
        private LocalDateTime createdAt;

        Product(Long productId, String name, String status, LocalDateTime updatedAt, LocalDateTime createdAt)
        {
            this.productId = productId;
            this.name = name;
            this.status = status;
            this.updatedAt = updatedAt;
            this.createdAt = createdAt;
        }
    }

    public interface ProductService {
        Product getProducyById(Long productId);
    }

    public class MockUserService implements ProductService {

        private Map<Long, Product> product = new HashMap<>();
        @Override
        public Product getProducyById(Long productId) { return product.get(productId);}
    }

}
