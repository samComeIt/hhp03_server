package hh.plus.server.product.controller;

import hh.plus.server.product.controller.dto.productOption.ProductOptionResponseDto;
import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Product Option API")
@RequestMapping("/api/option")
@RequiredArgsConstructor
public class ProductOptionController {
    private final ProductService productService;

    @Operation(summary = "Update product stock by option id", description = "Returns a product with a certain option")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The product option does not exist"),
    })
    @PatchMapping("/{productOptionId}")
    public ProductOptionResponseDto updateStockById(@PathVariable long productOptionId, @RequestBody long stock){
        return ProductOptionResponseDto.response(productOptionId, stock);
    }
}
