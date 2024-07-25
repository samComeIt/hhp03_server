package hh.plus.server.cart.domain.respository;

import hh.plus.server.cart.domain.entity.Cart;
import hh.plus.server.cart.domain.entity.Cart02;
import hh.plus.server.cart.service.dto.CartDto;
import hh.plus.server.cart.service.dto.CreateCartDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartJpaRepository extends CrudRepository<Cart02, Long> {
    //Optional<Cart> findById(Long cartId);
    Optional<Cart02> findById(Long cartId);

    Cart02 save(Cart02 cart02);

    void deleteById(Long cartId);

}
