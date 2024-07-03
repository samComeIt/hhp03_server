package hh.plus.server.controller.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {
    private Long userId;
    private Long amount;

    @Builder
    public UserRequestDto(Long userId, Long amount) {
        this.userId = userId;
        this.amount = amount;
    }
}
