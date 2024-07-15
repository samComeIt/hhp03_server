package hh.plus.server.product.service;

import hh.plus.server.order.service.OrderDetailRepository;
import hh.plus.server.product.controller.dto.productOption.ProductOptionResponseDto;
import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.domain.repository.ProductOptionRepository;
import hh.plus.server.product.domain.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final OrderDetailRepository orderDetailRepository;

    public Product getProductById(Long productId)
    {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
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

    public ProductOption getOptionById(Long productOptionId)
    {
        return productOptionRepository.findById(productOptionId)
                .orElseThrow(() -> new IllegalArgumentException("Product Option not found"));
    }

    @Transactional
    public ProductOptionResponseDto updateStockById(Long productOptionId, Long stock)
    {
        ProductOption productOption = productOptionRepository.findById(productOptionId)
                .orElseThrow(() -> new IllegalArgumentException("Product option not found"));

        productOption.minusStock(stock);
        productOptionRepository.save(productOption);

        return new ProductOptionResponseDto(productOption.getProductOptionId(), productOption.getStock());
    }

}
