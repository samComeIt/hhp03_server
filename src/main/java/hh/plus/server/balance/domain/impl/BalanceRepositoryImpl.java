package hh.plus.server.balance.domain.impl;

import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.domain.repository.BalanceJpaRepository;
import hh.plus.server.balance.service.BalanceRepository;
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
//    @Override
//    public Balance update(Long balanceId, Long balance) {return balanceJpaRepository.update(balanceId, balance);}


//    @Override
//    public Optional<Balance> findByBalanceId(Long balanceId) {return balanceJpaRepository.findByBalanceId(balanceId);}
}
