package hh.plus.server.balance.controller;

import hh.plus.server.balance.service.dto.BalanceResponseDto;
import hh.plus.server.balance.service.BalanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Balance API")
@RequestMapping("/api/balance")
@RequiredArgsConstructor
public class BalanceController {


    private static final Logger log = LoggerFactory.getLogger(BalanceController.class);

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
    public ResponseEntity<BalanceResponseDto> getBalance(@PathVariable long balanceId){
        log.info("Controller getBalanceByBalanceId : {}", balanceId);
        BalanceResponseDto balanceResponseDto = balanceService.getBalanceByBalanceId(balanceId);
        return ResponseEntity.ok(balanceResponseDto);
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
    public ResponseEntity<BalanceResponseDto> updateBalance(@PathVariable long balanceId, @RequestBody long amount){
        BalanceResponseDto balanceResponseDto = balanceService.updateBalance(balanceId, amount);
        return ResponseEntity.ok(balanceResponseDto);
    }

}
