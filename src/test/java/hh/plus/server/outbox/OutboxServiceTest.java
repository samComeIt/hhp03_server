package hh.plus.server.outbox;

import hh.plus.server.outbox.domain.OutboxStatus;
import hh.plus.server.outbox.domain.OutboxType;
import hh.plus.server.outbox.domain.entity.Outbox;
import hh.plus.server.outbox.domain.repository.OutboxRepository;
import hh.plus.server.outbox.service.OutboxService;
import hh.plus.server.outbox.service.dto.OutboxDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OutboxServiceTest {

    @InjectMocks
    private OutboxService outboxService;

    @Mock
    private OutboxRepository outboxRepository;

    @Test
    @DisplayName("특정 아이디 데이터 조회 테스트")
    public void testGetOutboxByIdSuccess()
    {
        // given
        Long outboxId = 1001L;
        Long orderId = 100L;
        String message = "Test Outbox";

        OutboxDto outboxDto = new OutboxDto(String.valueOf(orderId));

        Outbox outbox = Outbox.builder()
                .outboxId(outboxId)
                .id(outboxDto.id())
                .type(OutboxType.ORDER)
                .message(message)
                .status(OutboxStatus.INIT)
                .createdAt(LocalDateTime.now())
                .build();

        Outbox mockOutbox = outboxRepository.findById(outboxId)
                .orElseThrow(() -> new IllegalArgumentException("Outbox not found"));

        // when, then
        when(outboxRepository.findById(outboxId)).thenReturn(Optional.of(outbox));

        Long result = outboxService.getOutboxById(outboxId).getOutboxId();

        assertEquals(outboxId, result);
    }

    @Test
    @DisplayName("특정 아이디 상태 업데이트 실패 테스트")
    public void testUpdateOutboxStatusByIdFail()
    {
        // given
        Long outboxId = 1001L;
        Long orderId = 100L;
        String message = "Test Outbox";

        OutboxDto outboxDto = new OutboxDto(String.valueOf(orderId));

        Outbox outbox = Outbox.builder()
                .outboxId(outboxId)
                .id(outboxDto.id())
                .type(OutboxType.ORDER)
                .message(message)
                .status(OutboxStatus.PUBLISHED)
                .createdAt(LocalDateTime.now())
                .build();

        outboxService.update(outboxId);

        Optional<Outbox> events = outboxRepository.findById(outboxId);

        assertEquals("", events.get().getUpdatedAt());

    }
}
