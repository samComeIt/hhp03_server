package hh.plus.server.balance.service;

import hh.plus.server.balance.controller.dto.BalanceResponseDto;
import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.domain.repository.BalanceRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;

    @Transactional
    public BalanceResponseDto updateBalance(Long balanceId, Long amount)
    {
        log.info("updateBalance : {}, {}", balanceId, amount);

        Balance balance = balanceRepository.findById(balanceId)
                .orElseThrow(() -> new IllegalArgumentException("Balance not found"));

        balance.newBalance(amount);
        balanceRepository.save(balance);

        return new BalanceResponseDto(balance.getBalanceId(), balance.getBalance());
    }

    @Transactional(readOnly = true)
    public BalanceResponseDto getBalanceByBalanceId(Long balanceId) {

        log.info("getBalanceByBalanceId : {}", balanceId);

        Balance balance = balanceRepository.findById(balanceId)
                .orElseThrow(() -> new IllegalArgumentException("Balance not found"));

        return new BalanceResponseDto(balance.getBalanceId(), balance.getBalance());
    }

}
