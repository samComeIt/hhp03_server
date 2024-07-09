package hh.plus.server.balance.controller;

import hh.plus.server.balance.controller.dto.BalanceDto;
import hh.plus.server.balance.controller.dto.BalanceResponseDto;
import hh.plus.server.balance.domain.entity.Balance;
import hh.plus.server.balance.service.BalanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Balance API")
@RequestMapping("/api/balance")
@RequiredArgsConstructor
public class BalanceController {
    private final BalanceService balanceService;

    /**
     * 잔액 조회
     * @param balanceId
     * @return
     */
    @Operation(summary = "Get balance by id", description = "Returns a balance as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The balance does not exist"),
    })
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
    @Operation(summary = "Update balance by id and amount", description = "Returns updated balance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Not found - The balance does not exist")
    })
    @PatchMapping("/{balanceId}")
    public BalanceResponseDto updateBalance(@PathVariable long balanceId, @RequestBody long amount){
        return balanceService.updateBalance(balanceId, amount);
    }
}
