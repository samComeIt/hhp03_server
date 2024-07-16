package hh.plus.server.product.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ProductSearchRequestDto {
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public ProductSearchRequestDto(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
