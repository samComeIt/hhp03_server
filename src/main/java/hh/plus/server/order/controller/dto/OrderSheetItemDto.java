package hh.plus.server.order.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSheetItemDto {
    private Long orderSheetItemId;
    private String status;
    private Long totalCnt;
    private Long totalPrice;
    private Long singlePrice;
    private String pname;
    private String poptionName;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    private Long productId;
    private Long productOptionId;
}
