package hh.plus.server.balance.domain.impl;

import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.domain.repository.BalanceJpaRepository;
import hh.plus.server.balance.domain.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BalanceRepositoryImpl implements BalanceRepository {

    private final BalanceJpaRepository balanceJpaRepository;

    @Override
    public Optional<Balance> findById(Long balanceId) {return balanceJpaRepository.findById(balanceId);}

    @Override
    public Balance save(Balance balance) { return balanceJpaRepository.save(balance);}

}
