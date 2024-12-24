package hh.plus.server.order.service.dto;

import hh.plus.server.order.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

public record OrderSheetItemDto(
    Long orderSheetItemId,
    Long cartId,
    OrderStatus status,
    Long totalCnt,
    Long totalPrice,
     Long singlePrice,
     String pname,
     String poptionName,
     LocalDateTime updatedAt,
     LocalDateTime createdAt,
    Long productId,
    Long productOptionId) {
}
