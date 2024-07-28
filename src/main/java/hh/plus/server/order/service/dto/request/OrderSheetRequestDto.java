package hh.plus.server.order.service.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderSheetRequestDto {
    private LocalDateTime createdAt;

    private List<OrderSheetItemRequestDto> orderSheetItem;
}
