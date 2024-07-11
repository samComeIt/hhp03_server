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
        Optional<Balance> optionalBalance = balanceRepository.findById(balanceId);

        if (optionalBalance.isEmpty()) throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "balanceId does not exist"
        );

        Balance balance = optionalBalance.get();
        balance.newBalance(amount);
        balanceRepository.save(balance);

        return new BalanceResponseDto(balance.getBalanceId(), balance.getBalance());
    }

    public BalanceResponseDto getBalanceByBalanceId(Long balanceId) {

        Optional<Balance> optionalBalance = balanceRepository.findById(balanceId);

        if (optionalBalance.isEmpty()) throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "balanceId does not exist"
        );

        Balance balance = optionalBalance.get();
        return new BalanceResponseDto(balance.getBalanceId(), balance.getBalance());

    }

}
