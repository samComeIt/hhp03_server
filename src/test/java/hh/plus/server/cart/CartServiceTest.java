package hh.plus.server.cart;

import hh.plus.server.cart.domain.entity.Cart02;
import hh.plus.server.cart.domain.respository.CartRepository;
import hh.plus.server.cart.service.CartService;
import hh.plus.server.cart.service.dto.CartDtoMapper;
import hh.plus.server.common.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private Cart02 cart;

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    private final CartDtoMapper cartDtoMapper = new CartDtoMapper();

    @Test
    @DisplayName("카트 조회 실패")
    public void testGetCartByIdFail()
    {
        // given
        Cart02 cart02 = Cart02.builder()
                .userId(100L)
                .productId(100L)
                .productOptionId(1001L)
                .productName("아디다스 삼바")
                .productOptionName("250mm")
                .singlePrice(100000L)
                .totalPrice(100000L)
                .quantity(1L)
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        Cart02 cart = cartRepository.save(cart02);

        Long cartId = cart.getCartId();

        Cart02 mockCart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CustomException("Cart not found"));

        // when
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(mockCart));

        Long cartId02 = cartRepository.findById(cartId).get().getCartId();
        // then
        assertEquals(cartId02, cartId);
    }

    @Test
    @DisplayName("카트 조회 성공")
    public void testGetCartByIdSuccess()
    {

    }

    @Test
    @DisplayName("카트 삭제 실패")
    public void testDeleteCartByIdFail()
    {

    }

    @Test
    @DisplayName("카트 삭제 성공")
    public void testDeleteCartSuccess()
    {

    }

}
