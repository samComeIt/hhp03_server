package hh.plus.server.balance;

import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.domain.repository.BalanceRepository;
import hh.plus.server.balance.service.BalanceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BalanceOptimisticLockTest {

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceService balanceService;

    public BalanceOptimisticLockTest() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("잔액 충전 동시성 제어 테스트(Optimistic Locking)")
    public void testChargeBalanceOptimisticLock() throws InterruptedException {
        Logger logger = Logger.getLogger(getClass().getName());

        long balanceId = 100L;
        Balance balance = Balance.builder()
                .balanceId(balanceId)
                .balance(200L)
                .build();

        when(balanceRepository.findById(balanceId)).thenReturn(Optional.of(balance));

        for (int i = 0; i < 20; i++) {
            runConcurrentChargeOperationsOptimistic(balanceId, logger);
        }

        Thread.sleep(500);

        Balance finalBalance = balanceRepository.findById(balanceId).orElseThrow();

        assertEquals(2200L, finalBalance.getBalance());

        verify(balanceRepository, times(21)).findById(balanceId);
        verify(balanceRepository, times(20)).save(any(Balance.class));

    }

    private void runConcurrentChargeOperationsOptimistic(long balanceId, Logger logger) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            try {
                logger.info("Thread started: " + Thread.currentThread().getName());
                Balance balance = balanceRepository.findById(balanceId).orElseThrow();

                logger.info("Current balance before update in thread " + balance.getBalance());
                balance.newBalance(100L);
                balanceRepository.save(balance);

                logger.info("Updated balance in thread " + balance.getBalance());
            } catch (Exception e) {
                logger.info("Exception occurred in thread " + e.getMessage());
            }

        });

        executorService.shutdown();

        while (!executorService.isTerminated()) {
        }
    }
}