package hh.plus.server.balance.controller;

import hh.plus.server.balance.controller.dto.BalanceDto;
import hh.plus.server.balance.controller.dto.BalanceResponseDto;
import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/balance")
@RequiredArgsConstructor
public class BalanceController {
    private final BalanceService balanceService;

    /**
     * 잔액 조회
     * @param balanceId
     * @return
     */
    @GetMapping("/{balanceId}")
    public BalanceResponseDto getBalance(@PathVariable long balanceId){
        return balanceService.getBalanceByBalanceId(balanceId);
    }

    /**
     * 잔액 충전
     * @param balanceId
     * @param amount
     * @return
     */
    @PatchMapping("/{balanceId}")
    public BalanceResponseDto updateBalance(@PathVariable long balanceId, @RequestBody long amount){
        return balanceService.updateBalance(balanceId, amount);
        //return BalanceResponseDto.response(balanceId, amount);
    }
}
