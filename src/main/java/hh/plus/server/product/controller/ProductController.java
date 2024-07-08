package hh.plus.server.product.controller;

import hh.plus.server.product.controller.dto.ProductDetailResponseDto;
import hh.plus.server.product.controller.dto.ProductOrderResponseDto;
import hh.plus.server.product.controller.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
    /**
     * 관련된 상품 전체 조회
     * @param productId
     * @return
     */
    @GetMapping("/product/{productId}")
    public ProductResponseDto getProduct(@PathVariable long productId){
        return ProductResponseDto.response(productId, "상품01", Arrays.asList(100L, 200L, 300L));
    }

    /**
     * 상품 상세 조회
     * @param productId
     * @param productOptionId
     * @return
     */
    @GetMapping("/product/{productId}/detail/{productOptionId}")
    public ProductDetailResponseDto getProductOption(@PathVariable long productId, @PathVariable long productOptionId){
        return ProductDetailResponseDto.response(productId, "상품01", Arrays.asList(productOptionId));
    }

    /**
     * 상위 상품 조회
     * @return
     */
    @GetMapping("/products")
    public ProductOrderResponseDto getProductList(){
        return ProductOrderResponseDto.response(Arrays.asList(100L, 200L, 300L));
    }
}
