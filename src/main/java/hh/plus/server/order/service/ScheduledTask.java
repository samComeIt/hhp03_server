package hh.plus.server.order.service;

import hh.plus.server.common.service.KafkaProducerService;
import hh.plus.server.outbox.domain.OutboxStatus;
import hh.plus.server.outbox.domain.entity.Outbox;
import hh.plus.server.outbox.domain.repository.OutboxRepository;
import hh.plus.server.outbox.service.OutboxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledTask {
    private final KafkaProducerService kafkaProducerService;
    private final OutboxRepository outboxRepository;

    @Scheduled(fixedRate = 600000) // every 10 minutes
    public void performTask() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusMinutes(10);

        log.info("Scheduled start");

        List<Outbox> events = outboxRepository.findByStatusAndCreatedAtBetween(OutboxStatus.INIT, startDate, endDate);

        for (Outbox event : events) {
            try {
                // send Producer
                kafkaProducerService.sendMessage("order-topic", String.valueOf(event.getOutboxId()));
            } catch (Exception e) {
                log.info("Scheduled failed Outbox ID: {}", event.getOutboxId());
               event.newStatus(OutboxStatus.FAILED);
            } finally {
                event.changeUpatedAt();
                outboxRepository.save(event);
            }
        }

        log.info("Scheduled finish");
    }
}
