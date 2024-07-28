package hh.plus.server.balance;

import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.domain.repository.BalanceRepository;
import hh.plus.server.balance.service.BalanceService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BalanceIntegrationTest {

    @InjectMocks
    private BalanceService balanceService;

    @Autowired
    private BalanceRepository balanceRepository;

    @Test
    @Transactional
    @DisplayName("잔액 차감으로 마이너스 되는 업데이트 처리 실패 테스트")
    void testUpdateBalanceFail() throws Exception
    {
        Long balanceId = 100L;
        //given
        Balance balance = new Balance(balanceId, 2000L, LocalDateTime.now(), LocalDateTime.now());

        //when
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> {
                    balanceService.updateBalance(balanceId, -300L);
                }),
                CompletableFuture.runAsync(() -> {
                    balanceService.updateBalance(balanceId, -1000L);
                }),
                CompletableFuture.runAsync(() -> {
                    balanceService.updateBalance(balanceId, -400L);
                })
        ).join();

        // then
        Optional<Balance> optionalBalance = balanceRepository.findById(balanceId);
        assertTrue(optionalBalance.get().getBalance() >= 0);
    }
}
