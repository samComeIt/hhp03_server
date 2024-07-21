package hh.plus.server.product.service;

import hh.plus.server.cart.service.dto.CartItemDto;
import hh.plus.server.order.domain.repository.OrderDetailRepository;
import hh.plus.server.product.service.dto.ProductDto;
import hh.plus.server.product.service.dto.ProductOptionDto;
import hh.plus.server.product.service.dto.productOption.ProductOptionResponseDto;
import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.domain.repository.ProductOptionRepository;
import hh.plus.server.product.domain.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Transactional(readOnly = true)
    public Product getProductById(Long productId)
    {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Transactional(readOnly = true)
    public ProductDto getProductAndOptionById(Long productId, Long productOptionId)
    {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        ProductOption option = product.getProductOption().stream()
                .filter(opt -> opt.getProductOptionId().equals(productOptionId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Product Option not found"));

        ProductOptionDto productOptionDto = new ProductOptionDto(
                option.getProductOptionId(),
                option.getName(),
                option.getStock()
        );

        ProductDto productDto = new ProductDto(
                product.getProductId(),
                product.getName(),
                product.getProductOption().stream()
                        .filter(opt -> opt.getProductOptionId().equals(productOptionId))
                        .map(
                                opt2 -> new ProductOptionDto(
                                       opt2.getProductOptionId(),
                                       opt2.getName(),
                                       opt2.getStock()
                                )
                        ).toList()

        );

        return productDto;
    }

    @Transactional(readOnly = true)
    public List<Product> getTopSoldProduct(LocalDate startDate, LocalDate endDate)
    {
        return orderDetailRepository.findTopSoldProduct(startDate, endDate);
    }

    @Transactional(readOnly = true)
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
