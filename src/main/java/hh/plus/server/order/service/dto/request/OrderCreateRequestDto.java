package hh.plus.server.order.service.dto.request;

import java.util.List;

public record OrderCreateRequestDto(
        Long balanceId,
        Long orderSheetId,
        List<OrderItemCreateRequestDto> orderItemCreateRequestDto
) {

}
