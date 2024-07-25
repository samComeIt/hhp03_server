package hh.plus.server.balance.domain.repository;

import hh.plus.server.balance.domain.entity.Balance;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BalanceRepository {

    Optional<Balance> findById(Long balanceId);

    Balance save(Balance balance);
}
