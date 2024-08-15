package hh.plus.server.outbox.service;

import hh.plus.server.common.exception.CustomException;
import hh.plus.server.outbox.domain.OutboxStatus;
import hh.plus.server.outbox.domain.OutboxType;
import hh.plus.server.outbox.domain.entity.Outbox;
import hh.plus.server.outbox.domain.repository.OutboxRepository;
import hh.plus.server.outbox.service.dto.OutboxDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutboxService {
    private final OutboxRepository outboxRepository;

    @Transactional
    public Outbox add(OutboxDto outboxDto) {
        Outbox outbox = new Outbox(
                outboxDto.id()
                , OutboxType.ORDER
                , "Kafka Producer Sent"
                , OutboxStatus.INIT
                , LocalDateTime.now()
        );

        return outboxRepository.save(outbox);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(Long outboxId) {
        Outbox outbox = outboxRepository.findById(outboxId)
                .orElseThrow(() -> new CustomException("Outbox not found"));


        outbox.newStatus(OutboxStatus.PUBLISHED);
        outboxRepository.save(outbox);

        return;
    }

    public Outbox getOutboxById(Long outboxId) {
        Outbox outbox = outboxRepository.findById(outboxId)
                .orElseThrow(() -> new CustomException("Outbox not found"));

        return outbox;
    }

    public List<Outbox> getEventsByStatusAndDateRange(OutboxStatus status, LocalDateTime startDate, LocalDateTime endDate) {
        return outboxRepository.findByStatusAndCreatedAtBetween(status, startDate, endDate);
    }

}
