package hh.plus.server.controller.user;

import hh.plus.server.controller.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/point")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/{userId}")
    public UserResponseDto getPoint(@PathVariable long userId){ return UserResponseDto.response(userId, 200L);}

}
