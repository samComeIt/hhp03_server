package hh.plus.server.balance.controller.dto;

import java.time.LocalDateTime;

public record BalanceDto (Long balanceId, Long balance, LocalDateTime updatedAt, LocalDateTime createdAt)
{
    public static BalanceDto response(Long balanceId, Long balance)
    {
        return new BalanceDto(balanceId, balance, LocalDateTime.now(), LocalDateTime.now());
    }
}
