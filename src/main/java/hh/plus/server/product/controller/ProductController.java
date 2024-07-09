package hh.plus.server.product.controller;

import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.product.controller.dto.ProductDetailResponseDto;
import hh.plus.server.product.controller.dto.ProductOrderResponseDto;
import hh.plus.server.product.controller.dto.ProductResponseDto;
import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@RestController
@Tag(name = "Product API")
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 관련된 상품 전체 조회
     * @param productId
     * @return
     */
//    @GetMapping("/product/{productId}")
//    public ProductResponseDto getProduct(@PathVariable long productId){
//        return ProductResponseDto.response(productId, "상품01", Arrays.asList(100L, 200L, 300L));
//    }

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
    public Optional<Product> getProduct(@PathVariable long product_id){
        return productService.getProductById(product_id);
    }

    /**
     * 상품 상세 조회
     * @param productId
     * @param productOptionId
     * @return
     */
//    @Operation(summary = "Get product with a certain option by product id and option id", description = "Returns a product with a certain option")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
//            @ApiResponse(responseCode = "404", description = "Not found - The product does not exist"),
//    })
//    @GetMapping("/product/{productId}/detail/{productOptionId}")
//    public ProductDetailResponseDto getProductOption(@PathVariable long productId, @PathVariable long productOptionId){
//        return ProductDetailResponseDto.response(productId, "상품01", Arrays.asList(productOptionId));
//    }


    @Operation(summary = "Get product with a certain option by product id and option id", description = "Returns a product with a certain option")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The product does not exist"),
    })
    @GetMapping("/product/{productId}/detail/{productOptionId}")
    public Product getProductAndOptioById(@PathVariable long productId, @PathVariable long productOptionId){
        return productService.getProductAndOptioById(productId, productOptionId);
    }

//    @GetMapping("/product/{productId}/detail/{productOptionId}")
//    public Optional<ProductOption> findProductWithOption(@PathVariable long productId, @PathVariable long productOptionId){
//        return productService.getOptionById(productId, productOptionId);
//    }

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
    public ProductOrderResponseDto getProductList(){
        return ProductOrderResponseDto.response(Arrays.asList(100L, 200L, 300L));
    }
}
