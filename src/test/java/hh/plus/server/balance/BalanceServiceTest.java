package hh.plus.server.balance;

import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.domain.repository.BalanceRepository;
import hh.plus.server.balance.service.BalanceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceTest {

    @InjectMocks
    private BalanceService balanceService;

    @Mock
    private BalanceRepository balanceRepository;

    private Balance balance(long balanceId, long balance, LocalDateTime updatedAt, LocalDateTime createdAt) {
        return Balance.builder()
                .balanceId(balanceId)
                .balance(balance)
                .build();
    }

    @Test
    @DisplayName("잔액 조회 실패")
    public void testGetBalanceByIdFail()
    {
        //  given
        Long balanceId = 10L;
        Balance balance = balance(balanceId, 1000L, LocalDateTime.now(), LocalDateTime.now());
        when(balanceRepository.findById(balanceId).get()).thenReturn(balance);

        // when, then
        assertEquals(balanceRepository.findById(balanceId), balance);
    }

    @Test
    @DisplayName("잔액 충전 실패")
    public void testPatchBalanceByIdAndAmountFail()
    {
        // given
        Long balanceId = 10L;
        Balance balance = balance(balanceId, 1000L, LocalDateTime.now(), LocalDateTime.now());

        Balance mockBalance = balanceRepository.findById(balanceId).get();
        mockBalance.setBalance(-100L);

        when(balanceRepository.save(mockBalance)).thenReturn(balance);

        // when, then
        assertEquals(balanceRepository.findById(balanceId), balance);
    }
}
