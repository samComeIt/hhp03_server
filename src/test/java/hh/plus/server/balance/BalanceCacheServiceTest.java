package hh.plus.server.balance;

import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.service.BalanceService;
import hh.plus.server.balance.service.dto.BalanceResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@EnableCaching
public class BalanceCacheServiceTest {

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void setUp() {
        Cache cache = cacheManager.getCache("myBalance");
        if (cache != null) {
            cache.clear(); // Clear cache before each test
        }
    }

    @Test
    public void testCacheUsage() {
        // given
        Long balanceId = 100L;
        Long amount = 200L;

        Balance balance = Balance.builder()
                .balanceId(balanceId)
                .balance(amount)
                .build();

        BalanceResponseDto firstCall = balanceService.getBalanceByBalanceId(balanceId);
        BalanceResponseDto secondCall = balanceService.getBalanceByBalanceId(balanceId);

        // when, then
        assertEquals(firstCall, secondCall, "Both calls should return the same object");
        assertTrue(firstCall == secondCall, "Both calls should return the same instance from cache");

        Cache cache = cacheManager.getCache("myBalance");
        assertNotNull(cache, "Cache 'balances' should be initialized");
        assertNotNull(cache.get(balanceId), "Cache should contain an entry for balanceId");
    }
}
