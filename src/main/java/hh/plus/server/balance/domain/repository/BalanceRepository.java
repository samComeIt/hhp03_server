package hh.plus.server.balance.domain.repository;

import hh.plus.server.balance.domain.entity.Balance;

import java.util.Optional;

public interface BalanceRepository {
    Optional<Balance> findById(Long balanceId);

    Balance save(Balance balance);
}
