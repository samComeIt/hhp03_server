package hh.plus.server.balance;

import com.fasterxml.jackson.databind.ObjectMapper;
import hh.plus.server.balance.controller.BalanceController;
import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.service.BalanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;

@WebMvcTest(BalanceController.class)
public class BalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private BalanceService balanceService;

    @Test
    @DisplayName("특정한 아이디 잔액 조회 실패")
    public void testGetBalanceByIdSuccess() throws Exception{
        Long balanceId = 99L;
        Balance balance = new Balance(balanceId, 200L, LocalDateTime.now(), LocalDateTime.now());

        when(balanceService.getBalanceByBalanceId(balanceId).balance()).thenReturn(balance.getBalance());

        mockMvc.perform(get("/api/balance/{balanceId}", balanceId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balanceId").value(100))
                .andExpect(jsonPath("$.balance").value(200));

    }




}
