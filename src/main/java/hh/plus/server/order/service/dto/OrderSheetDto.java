package hh.plus.server.order.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

public record OrderSheetDto (
        Long orderSheetId,

        List<OrderSheetItemDto> orderSheetItem,
        LocalDateTime createdAt
){}
