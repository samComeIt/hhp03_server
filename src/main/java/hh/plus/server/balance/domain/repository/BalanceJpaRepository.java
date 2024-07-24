package hh.plus.server.balance.domain.repository;

import hh.plus.server.balance.domain.entity.Balance;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceJpaRepository extends JpaRepository<Balance, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM Balance b WHERE b.balanceId = :balanceId")
    Optional<Balance> findByIdWithPessimisticWriteLock(@Param("balanceId") Long balanceId);

    Optional<Balance> findById(Long balanceId);

    Balance save(Balance balance);
}
