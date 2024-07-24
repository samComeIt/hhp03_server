package hh.plus.server.balance;

import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.domain.repository.BalanceRepository;
import hh.plus.server.balance.service.BalanceService;
import hh.plus.server.balance.service.dto.BalanceResponseDto;
import hh.plus.server.payment.domain.PaymentStatus;
import hh.plus.server.payment.domain.entity.Payment;
import hh.plus.server.payment.domain.repository.PaymentRepository;
import hh.plus.server.payment.service.PaymentService;
import org.apache.catalina.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BalancePessimisticLockTest {

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceService balanceService;

    public BalancePessimisticLockTest() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("잔액 충전 동시성 제어 테스트")
    public void testChargeBalancePessimisticLock() throws InterruptedException {
        long balanceId = 100L;
        Balance balance = Balance.builder()
                .balanceId(balanceId)
                .balance(200L)
                .build();

        when(balanceRepository.findByIdWithPessimisticWriteLock(balanceId)).thenReturn(Optional.of(balance));

        for (int i = 0; i < 20; i++) {
            runConcurrentChargeOperations(balanceId);
        }

        Thread.sleep(500);

        Balance finalBalance = balanceRepository.findByIdWithPessimisticWriteLock(balanceId).orElseThrow();

        assertEquals(2200L, finalBalance.getBalance());

        verify(balanceRepository, times(21)).findByIdWithPessimisticWriteLock(balanceId);
        verify(balanceRepository, times(20)).save(any(Balance.class));

    }

    private void runConcurrentChargeOperations(long balanceId) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            try {
                latch.await();
                balanceService.updateBalance(balanceId, 100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        latch.countDown();

        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }
}
