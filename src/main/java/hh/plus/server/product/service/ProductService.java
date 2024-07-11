package hh.plus.server.product.service;

import hh.plus.server.order.service.OrderDetailRepository;
import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.domain.repository.ProductOptionRepository;
import hh.plus.server.product.domain.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

    public Optional<Product> getProductById(Long productId)
    {
        Optional<Product> productOptional = productRepository.findById(productId);
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

    public List<Product> getTopSoldProduct(LocalDate startDate, LocalDate endDate)
    {
        return orderDetailRepository.findTopSoldProduct(startDate, endDate);
    }

}
