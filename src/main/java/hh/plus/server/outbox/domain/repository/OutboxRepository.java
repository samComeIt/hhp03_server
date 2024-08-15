package hh.plus.server.outbox.domain.repository;

import hh.plus.server.outbox.domain.OutboxStatus;
import hh.plus.server.outbox.domain.entity.Outbox;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OutboxRepository {

    Outbox save(Outbox outbox);

    Optional<Outbox> findById(Long outboxId);

    List<Outbox> findByStatusAndCreatedAtBetween(OutboxStatus status, LocalDateTime startDate, LocalDateTime endDate);
}
