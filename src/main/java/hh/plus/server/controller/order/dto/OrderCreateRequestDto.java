package hh.plus.server.controller.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateRequestDto
{
    private Long productId;
    private Long productOptionId;

    @Builder
    public OrderCreateRequestDto(Long productId, Long productOptionId)
    {
        this.productId = productId;
        this.productOptionId = productOptionId;
    }
}
