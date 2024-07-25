package hh.plus.server.redis;

import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.domain.repository.BalanceRepository;
import hh.plus.server.balance.service.BalanceService;
import hh.plus.server.common.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BalanceServiceTest {

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private BalanceRepository balanceRepository;

    @Test
    public void testBalanceUpdateWithLock() throws InterruptedException {
        Long balanceId = 1L;
        Balance balance = Balance.builder()
                .balanceId(balanceId)
                .balance(100L)
                .build();

        balanceRepository.save(balance);

        String lockKey = "balance-" + balanceId;
        CountDownLatch latch = new CountDownLatch(1);

        Thread thread1 = new Thread(() -> {
            try {
                balanceService.updateBalanceWithLock(balanceId, 50L);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                latch.await(); // Wait for thread1 to complete
                balanceService.updateBalanceWithLock(balanceId, -30L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        Balance updatedBalance = balanceRepository.findById(balanceId)
                .orElseThrow(() -> new CustomException("Balance not found"));

        assertEquals(120L, updatedBalance.getBalance());

        assertTrue(true);
    }
}
