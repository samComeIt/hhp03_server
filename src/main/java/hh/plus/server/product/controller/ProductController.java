package hh.plus.server.product.controller;

import hh.plus.server.product.service.dto.ProductDto;
import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    public ProductDto getProductAndOptionById(@PathVariable long productId, @PathVariable long productOptionId){
        return productService.getProductAndOptionById(productId, productOptionId);
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
    public List<Product> getProductList(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate){
        return productService.getTopSoldProduct(startDate, endDate);
    }
}
