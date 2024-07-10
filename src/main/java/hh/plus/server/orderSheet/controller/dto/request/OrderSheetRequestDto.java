package hh.plus.server.orderSheet.controller.dto.request;

import hh.plus.server.orderSheet.controller.dto.OrderSheetItemDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderSheetRequestDto {
    private LocalDateTime createdAt;

    private List<OrderSheetItemRequestDto> orderSheetItem;
}
