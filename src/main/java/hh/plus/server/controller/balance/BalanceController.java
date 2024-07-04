package hh.plus.server.controller.balance;

import hh.plus.server.controller.balance.dto.BalanceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/balance")
@RequiredArgsConstructor
public class BalanceController {

    /**
     * 잔액 조회
     * @param balanceId
     * @return
     */
    @GetMapping("/{balanceId}")
    public BalanceResponseDto getBalance(@PathVariable long balanceId){ return BalanceResponseDto.response(balanceId, 200L);}

    /**
     * 잔액 충전
     * @param balanceId
     * @param amount
     * @return
     */
    @PatchMapping("/{balanceId}")
    public BalanceResponseDto addBalance(@PathVariable long balanceId, @RequestBody long amount){ return BalanceResponseDto.response(balanceId, amount);}

}
