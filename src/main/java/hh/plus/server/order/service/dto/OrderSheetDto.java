package hh.plus.server.order.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class OrderSheetDto {
    private Long orderSheetId;
    private LocalDateTime createdAt;

    private List<OrderSheetItemDto> orderSheetItem;
}
