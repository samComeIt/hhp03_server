package hh.plus.server.balance.infrastructure;

import hh.plus.server.balance.domain.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceJpaRepository extends JpaRepository<Balance, Long> {
//    Balance update(Long balanceId, Long balance);

//    Optional<Balance> findByBalanceId(Long balanceId);

    Optional<Balance> findById(Long balanceId);

    Balance save(Balance balance);
}
