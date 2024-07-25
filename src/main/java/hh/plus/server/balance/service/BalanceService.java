package hh.plus.server.balance.service;

import hh.plus.server.balance.service.dto.BalanceResponseDto;
import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.domain.repository.BalanceRepository;
import hh.plus.server.common.exception.CustomException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceService {

    private final RedisTemplate<String, String> redisTemplate;
    private final BalanceRepository balanceRepository;

    @Transactional(rollbackFor = Exception.class)
    public BalanceResponseDto updateBalance(Long balanceId, Long amount)
    {
        log.info("updateBalance balanceId: {}, balance: {}", balanceId, amount);

        Balance balance = balanceRepository.findById(balanceId)
                .orElseThrow(() -> new CustomException("Balance not found"));

        try {
            Thread.sleep(100);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        log.info("before update balanceId: {}, balance: {}", balanceId, balance.getBalance());

        balance.newBalance(amount);
        balanceRepository.save(balance);

        return new BalanceResponseDto(balance.getBalanceId(), balance.getBalance());
    }

    @Transactional(readOnly = true)
    public BalanceResponseDto getBalanceByBalanceId(Long balanceId) {

        log.info("getBalanceByBalanceId : {}", balanceId);

        Balance balance = balanceRepository.findById(balanceId)
                .orElseThrow(() -> new CustomException("Balance not found"));

        return new BalanceResponseDto(balance.getBalanceId(), balance.getBalance());
    }

    public void updateBalanceWithLock(Long balanceId, Long amountToUpdate) throws InterruptedException {
        String lockKey = "balance-" + balanceId;

        boolean locked = false;
        try {
            locked = redisTemplate.opsForValue().setIfAbsent(lockKey, "locked");
            if (locked) {
                redisTemplate.expire(lockKey, 10, TimeUnit.SECONDS);

                Balance balance = balanceRepository.findById(balanceId)
                        .orElseThrow(() -> new CustomException("Balance not found"));

                balance.newBalance(amountToUpdate);
                balanceRepository.save(balance);
            } else {
                throw new RuntimeException("Could not acquire lock");
            }
        } finally {
            if (locked) {
                redisTemplate.delete(lockKey);
            }
        }
    }

}
