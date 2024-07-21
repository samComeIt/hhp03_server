package hh.plus.server.cart.service.dto;

import hh.plus.server.cart.domain.entity.Cart;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CartDtoMapper implements Function<Cart, CartDto> {
    @Override
    public CartDto apply(Cart cart) {
        return new CartDto(
                cart.getCartId(),
                cart.getCartItem().stream()
                        .map(cartItem -> new CartItemDto(
                                cartItem.getCartItemId(),
                                cartItem.getCart().getCartId(),
                                cartItem.getProductId(),
                                cartItem.getProductOptionId(),
                                cartItem.getProductName(),
                                cartItem.getProductOptionName(),
                                cartItem.getSinglePrice(),
                                cartItem.getTotalPrice(),
                                cartItem.getQuantity(),
                                cartItem.getUpdatedAt(),
                                cartItem.getCreatedAt()
                        )).toList(),
                cart.getUpdatedAt(),
                cart.getCreatedAt()
        );
    }


}
