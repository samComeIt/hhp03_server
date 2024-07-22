package hh.plus.server.order.service.dto.request;

import java.util.List;

public record OrderSheetCreateRequestDto(
        Long userId,
        List<Long> cartId
) {
}
