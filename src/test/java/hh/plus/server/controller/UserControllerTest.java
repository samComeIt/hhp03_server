package hh.plus.server.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("유저 잔액 조회 실패 케이스")
    void testGetUserCurPointFail() throws Exception {
        // given
        Long userId = 100L;
        Long point = 0L;
        User user = new User(userId, point);

        // when
        when(userService.getUserById(userId)).thenReturn(user);

        // Performing GET request to "/api/point/{userId}"
        // then
        mockMvc.perform(get("/api/point/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    public class User {
        private Long userId;
        private Long point;

        User(Long userId, Long point)
        {
            this.userId = userId;
            this.point = point;
        }
    }

    public interface UserService {
        User getUserById(Long userId);
    }

    public class MockUserService implements UserService {
        private Map<Long, User> users = new HashMap<>();
        @Override
        public User getUserById(Long userId)
        {
            return users.get(userId);
        }
    }
}
