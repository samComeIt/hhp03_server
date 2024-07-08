package hh.plus.server.balance.service;

import hh.plus.server.balance.domain.entity.Balance;

import java.util.Optional;

public interface BalanceRepository {
//    Balance update(Long balanceId, Long balance);
//
//    Optional<Balance> findByBalanceId(Long balanceId);
    Optional<Balance> findById(Long balanceId);

    Balance save(Balance balance);
}
