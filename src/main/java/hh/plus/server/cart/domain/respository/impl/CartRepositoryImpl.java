package hh.plus.server.cart.domain.respository.impl;

import hh.plus.server.cart.domain.entity.Cart;
import hh.plus.server.cart.domain.entity.Cart02;
import hh.plus.server.cart.domain.respository.CartJpaRepository;
import hh.plus.server.cart.domain.respository.CartRepository;
import hh.plus.server.cart.service.dto.CartDto;
import hh.plus.server.cart.service.dto.CreateCartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CartRepositoryImpl implements CartRepository {
    private final CartJpaRepository cartJpaRepository;

//    @Override
//    public Optional<Cart> findById(Long cartId) {return cartJpaRepository.findById(cartId);}

    @Override
    public Optional<Cart02> findById(Long cartId) {return cartJpaRepository.findById(cartId);}

    @Override
    public Cart02 save(Cart02 cart02) { return cartJpaRepository.save(cart02); }

    @Override
    public void deleteById(Long cartId) { cartJpaRepository.deleteById(cartId);}

}
