package hh.plus.server.balance;

import hh.plus.server.balance.service.dto.BalanceResponseDto;
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
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceTest {

    @InjectMocks
    private BalanceService balanceService;

    @Mock
    private BalanceRepository balanceRepository;

    @Test
    @DisplayName("특정 아이디 잔액 조회 실패")
    public void testGetBalanceByIdFail()
    {
        //  given
        Long balanceId = 10L;
        Long amount = 200L;

        Balance balance = Balance.builder()
                .balanceId(balanceId)
                .balance(200L)
                .build();

        Balance mockBalance = balanceRepository.findById(balanceId)
                .orElseThrow(() -> new IllegalArgumentException("Balance not found"));

        when(balanceRepository.findById(balanceId)).thenReturn(Optional.of(balance));

        Long result = balanceService.getBalanceByBalanceId(balanceId).balance();

        // when, then
        assertEquals(amount, result);
    }

    @Test
    @DisplayName("특정 아이디 잔액 충전 실패 테스트")
    public void testPatchBalanceByIdAndAmountFail()
    {
        // given
        Long balanceId = 10L;
        Balance balance = Balance.builder()
                .balanceId(balanceId)
                .balance(200L)
                .build();

        Balance mockBalance = balanceRepository.findById(balanceId)
                .orElseThrow(() -> new IllegalArgumentException("Balance not found"));
        mockBalance.newBalance(-100L);

        when(balanceRepository.save(mockBalance)).thenReturn(balance);
        Long result = balanceService.getBalanceByBalanceId(balanceId).balance();

        // when, then
        assertEquals(result, 100L);
    }

    @Test
    @DisplayName("특정 아이디 잔액 조회 성공 케이스")
    public void testFindBalanceByIdSuccess() throws Exception {
        // given
        Long balanceId = 100L;

        Balance balance = Balance.builder()
                .balanceId(balanceId)
                .balance(200L)
                .build();

        // when
        when(balanceRepository.findById(balanceId)).thenReturn(Optional.of(balance));
        BalanceResponseDto result = balanceService.getBalanceByBalanceId(balanceId);

        // then
        assertEquals(balance.getBalance(), result.balance());
    }

    @Test
    @DisplayName("특정 아이디 잔액 업데이트 조회 실패 케이스")
    public void testUpdateBalanceByIdFail() throws Exception {
        // given
        Long balanceId = 100L;

        Balance balance = new Balance(balanceId, 200L, LocalDateTime.now(), LocalDateTime.now());

        // when, then
        assertThrows(
                RuntimeException.class, () -> balance.newBalance(-300L)
        );
    }

}
