package hh.plus.server.balance.domain.repository;

import hh.plus.server.balance.domain.entity.Balance;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BalanceJpaRepository extends JpaRepository<Balance, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM Balance b WHERE b.balanceId = :balanceId")
    Optional<Balance> findByIdWithPessimisticWriteLock(@Param("balanceId") Long balanceId);

    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
    Optional<Balance> findById(Long balanceId);

    @Transactional
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Balance save(Balance balance);
}
