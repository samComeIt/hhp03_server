package hh.plus.server.product.controller;

import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.product.controller.dto.ProductDetailResponseDto;
import hh.plus.server.product.controller.dto.ProductOrderResponseDto;
import hh.plus.server.product.controller.dto.ProductResponseDto;
import hh.plus.server.product.controller.dto.ProductSearchRequestDto;
import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Product API")
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 관련된 상품 전체 조회
     * @param product_id
     * @return
     */
    @Operation(summary = "Get product by id", description = "Returns a product as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The product does not exist"),
    })
    @GetMapping("/product/{product_id}")
    public Product getProduct(@PathVariable long product_id){
        return productService.getProductById(product_id);
    }

    @Operation(summary = "Get product with a certain option by product id and option id", description = "Returns a product with a certain option")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The product does not exist"),
    })
    @GetMapping("/product/{productId}/detail/{productOptionId}")
    public Product getProductAndOptioById(@PathVariable long productId, @PathVariable long productOptionId){
        return productService.getProductAndOptioById(productId, productOptionId);
    }

    /**
     * 상위 상품 조회
     * @return
     */
    @Operation(summary = "Get list of popular products", description = "Returns a list of products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The product does not exist"),
    })
    @GetMapping("/products")
    public List<Product> getProductList(@RequestBody ProductSearchRequestDto productSearchRequestDto){
        return productService.getTopSoldProduct(productSearchRequestDto.getStartDate(), productSearchRequestDto.getEndDate());
    }
}
