package hh.plus.server.controller.balance.dto;

public record BalanceResponseDto(
        Long balanceId,
        Long balance
){
    public static BalanceResponseDto response(Long balanceId, Long balance)
    {
        return new BalanceResponseDto(balanceId, balance);
    }
}
