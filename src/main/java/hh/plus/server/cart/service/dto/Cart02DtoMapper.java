package hh.plus.server.cart.service.dto;

import hh.plus.server.cart.domain.entity.Cart;
import hh.plus.server.cart.domain.entity.Cart02;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class Cart02DtoMapper implements Function<Cart02, Cart02Dto> {
    @Override
    public Cart02Dto apply(Cart02 cart) {
        return new Cart02Dto(
                cart.getCartId(),
                cart.getProductId(),
                cart.getProductOptionId(),
                cart.getProductName(),
                cart.getProductOptionName(),
                cart.getSinglePrice(),
                cart.getTotalPrice(),
                cart.getQuantity()
        );
    }


}
