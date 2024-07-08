package hh.plus.server.balance.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BalanceRequestDto {
    private Long balanceId;
    private Long amount;

    @Builder
    public BalanceRequestDto(Long balanceId, Long amount) {
        this.balanceId = balanceId;
        this.amount = amount;
    }
}
