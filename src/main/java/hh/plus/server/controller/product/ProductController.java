package hh.plus.server.controller.product;

import hh.plus.server.controller.product.dto.ProductDetailResponseDto;
import hh.plus.server.controller.product.dto.ProductOrderResponseDto;
import hh.plus.server.controller.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public ProductResponseDto getProduct(@PathVariable long productId){ return ProductResponseDto.response(productId);}

    /**
     * 상품 상세 조회
     * @param productId
     * @param productOptionId
     * @return
     */
    @GetMapping("/product/{productId}/detail/{productOptionId}")
    public ProductDetailResponseDto getProductOption(@PathVariable long productId, @PathVariable long productOptionId){
        return ProductDetailResponseDto.response(productId, productOptionId);
    }

    /**
     * 상위 상품 조회
     * @return
     */
    @GetMapping("/products")
    public ProductOrderResponseDto getProductList(){
        return ProductOrderResponseDto.response();
    }
}
