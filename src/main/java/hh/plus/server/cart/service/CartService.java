package hh.plus.server.cart.service;


import hh.plus.server.cart.domain.entity.Cart;
import hh.plus.server.cart.domain.entity.Cart02;
import hh.plus.server.cart.domain.entity.CartItem;
import hh.plus.server.cart.domain.respository.CartRepository;
import hh.plus.server.cart.service.dto.*;
import hh.plus.server.common.exception.CustomException;
import hh.plus.server.product.domain.entity.Product;
import hh.plus.server.product.domain.entity.ProductOption;
import hh.plus.server.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartDtoMapper cartDtoMapper;

    private final ProductService productService;

//    public List<CartDto> getCartById(Long cartId) {
//        Optional<Cart> cart = cartRepository.findById(cartId);
//
//        return cart.stream()
//                .map(cartDtoMapper).collect(Collectors.toList());
//    }

    public Cart02Dto getCart02ById(Long cartId) {
        Cart02 cart02 = cartRepository.findById(cartId)
                .orElseThrow(()-> new CustomException("Cart not found"));

        return new Cart02Dto(cartId,
                cart02.getProductId(),
                cart02.getProductOptionId(),
                cart02.getProductName(),
                cart02.getProductOptionName(),
                cart02.getSinglePrice(),
                cart02.getTotalPrice(),
                cart02.getQuantity());
    }

    public Cart02 createCart(CartCreateRequestDto cartCreateRequestDto) {
        // find product exist by productId, productOptionId
        // check stock is valid
        // add to CartDto

        ProductOption productOption = productService.getOptionById(
                cartCreateRequestDto.productOptionId()
                );

        productOption.isStockAvailable(cartCreateRequestDto.quantity());

        Cart02 cart02 = new Cart02(
                cartCreateRequestDto.userId(),
                productOption.getProduct().getProductId(),
                cartCreateRequestDto.productOptionId(),
                productOption.getProduct().getName(),
                productOption.getName(),
                productOption.getPrice(),
                productOption.getPrice() * cartCreateRequestDto.quantity(),
                cartCreateRequestDto.quantity(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return cartRepository.save(cart02);
    }

    public void deleteCart(Long cartId)
    {
        cartRepository.deleteById(cartId);
    }
}
