package hh.plus.server.product.service;

import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.domain.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
