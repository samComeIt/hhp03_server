package hh.plus.server.product.service;

import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.domain.entity.ProductOption;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductOptionRepository productOptionRepository;

    public Optional<Product> getProductById(Long productId)
    {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional;
    }

    public Optional<ProductOption> getOptionById(Long productOptionId)
    {
        Optional<ProductOption> productOptional = productOptionRepository.findById(productOptionId);
        return productOptional;
    }

    public Product getProductAndOptioById(Long productId, Long productOptionId)
    {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        ProductOption option = product.getProductOption().stream()
                .filter(opt -> opt.getProductOptionId().equals(productOptionId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Product Option not found"));

        product.setProductOption(Collections.singletonList(option));

        return product;
    }

}
