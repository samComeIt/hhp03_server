package hh.plus.server.order.controller.dto;

import hh.plus.server.order.config.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateRequestDto
{
    private Long productId;
    private Long productOptionId;
    private OrderStatus status;



    @Builder
    public OrderCreateRequestDto(Long productId, Long productOptionId)
    {
        this.productId = productId;
        this.productOptionId = productOptionId;
    }
}
