package hh.plus.server.balance.domain.repository;

import hh.plus.server.balance.domain.entity.Balance;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BalanceJpaRepository extends CrudRepository<Balance, Long> {

    Optional<Balance> findById(Long balanceId);

    Balance save(Balance balance);
}
