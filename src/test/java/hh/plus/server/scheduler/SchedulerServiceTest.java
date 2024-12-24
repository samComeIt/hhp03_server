package hh.plus.server.scheduler;


import hh.plus.server.order.service.ScheduledTask;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SchedulerServiceTest {

    @InjectMocks
    private ScheduledTask scheduledTask;

    @Mock
    private OutboxRepository outboxRepository;

    @Test
    @DisplayName("스케줄러 테스트")
    public void testSchedulerTask() {
        // given
        Long outboxId = 1001L;
        Long orderId = 100L;
        String message = "Test Outbox Scheduler";

        LocalDateTime startDate = LocalDateTime.now().minusMinutes(10);
        LocalDateTime now = LocalDateTime.now();

        OutboxDto outboxDto = new OutboxDto(String.valueOf(orderId));

        Outbox outbox = Outbox.builder()
                .outboxId(outboxId)
                .id(outboxDto.id())
                .type(OutboxType.ORDER)
                .message(message)
                .status(OutboxStatus.INIT)
                .createdAt(now)
                .build();

        when(outboxRepository.findByStatusAndCreatedAtBetween(OutboxStatus.INIT, startDate, now)).thenReturn(Collections.singletonList(outbox));

        // when
        scheduledTask.performTask();

        // then
        Mockito.verify(outboxRepository, Mockito.times(2)).save(Mockito.argThat(entry ->
                entry.getId().equals(orderId) && entry.getStatus().equals(OutboxStatus.PUBLISHED)
        ));
    }
}
