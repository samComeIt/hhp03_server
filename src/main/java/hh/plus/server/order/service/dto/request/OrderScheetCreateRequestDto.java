package hh.plus.server.order.service.dto.request;

import java.util.List;

public record OrderScheetCreateRequestDto(
        Long userId,
        List<Long> cartId
) {
}
