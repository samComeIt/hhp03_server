package hh.plus.server.balance.service;

import hh.plus.server.balance.controller.dto.BalanceResponseDto;
import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.domain.repository.BalanceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;

    @Transactional
    public BalanceResponseDto updateBalance(Long balanceId, Long amount)
    {
        Balance balance = balanceRepository.findById(balanceId)
                .orElseThrow(() -> new IllegalArgumentException("Balance not found"));

        balance.newBalance(amount);
        balanceRepository.save(balance);

        return new BalanceResponseDto(balance.getBalanceId(), balance.getBalance());
    }

    public BalanceResponseDto getBalanceByBalanceId(Long balanceId) {

        Balance balance = balanceRepository.findById(balanceId)
                .orElseThrow(() -> new IllegalArgumentException("Balance not found"));

        return new BalanceResponseDto(balance.getBalanceId(), balance.getBalance());
    }

}
