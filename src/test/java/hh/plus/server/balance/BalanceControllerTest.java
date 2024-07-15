package hh.plus.server.balance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class BalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BalanceService balanceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("유저 잔액 조회 실패 케이스")
    void testGetUserCurPointFail() throws Exception {
        // given
        Long balanceId = 100L;
        Long point = 0L;
        Balance balance = new Balance(balanceId, point);

        // when
        when(balanceService.getBalanceById(balanceId)).thenReturn(balance);

        // Performing GET request to "/api/point/{balanceId}"
        // then
        mockMvc.perform(get("/api/balance/{balanceId}", balanceId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.balance").value(point));
    }

    @Test
    @DisplayName("유저 잔액 업데이트 실패 케이스")
    void testPatchUserCurPointFail() throws Exception {
        // given
        Long balanceId = 100L;
        Long point = 0L;
        Balance balance = new Balance(balanceId, point);

        // when
        when(balanceService.getBalanceById(balanceId)).thenReturn(balance);

        // Performing Patch request to "/api/point/{balanceId}"
        // then
        mockMvc.perform(patch("/api/balance/{balanceId}", balanceId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.balance").value(point));
    }

    public class Balance {
        private Long balanceId;
        private Long balance;

        Balance(Long balanceId, Long balance)
        {
            this.balanceId = balanceId;
            this.balance = balance;
        }
    }

    public interface BalanceService {
        Balance getBalanceById(Long balanceId);

        Balance save(Balance balance);
    }

    public class MockUserService implements BalanceService {
        private Map<Long, Balance> balance = new HashMap<>();
        @Override
        public Balance getBalanceById(Long userId)
        {
            return balance.get(userId);
        }

        @Override
        public Balance save(Balance balanceData) {
            Balance balance1 = new Balance(balanceData.balanceId, balanceData.balance);
            balance.put(balance1.balanceId, balance1);
            return balance.get(balance1.balanceId);
        }

    }
}