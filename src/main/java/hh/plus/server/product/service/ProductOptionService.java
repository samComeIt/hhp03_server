package hh.plus.server.product.service;

import hh.plus.server.balance.controller.dto.BalanceResponseDto;
import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.product.controller.dto.productOption.ProductOptionResponseDto;
import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.domain.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductOptionService {

    private final ProductOptionRepository productOptionRepository;
    public Optional<ProductOption> getOptionById(Long productOptionId)
    {
        Optional<ProductOption> productOptional = productOptionRepository.findById(productOptionId);
        return productOptional;
    }

    public ProductOptionResponseDto updateStockById(Long productOptionId, Long stock)
    {
        Optional<ProductOption> optionalProductOption = productOptionRepository.findById(productOptionId);

        if (optionalProductOption.isEmpty()) throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "productOptionId does not exist"
        );

        ProductOption productOption = optionalProductOption.get();
        productOption.minusStock(stock);
        productOptionRepository.save(productOption);

        return new ProductOptionResponseDto(productOption.getProductOptionId(), productOption.getStock());
    }
}
