package hh.plus.server.controller.user.dto;

public record UserResponseDto(
        Long userId,
        Long point
){
    public static UserResponseDto response(Long userId, Long point)
    {
        return new UserResponseDto(userId, point);
    }
}
