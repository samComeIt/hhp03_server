package hh.plus.server.outbox.domain.repository.impl;

import hh.plus.server.outbox.domain.OutboxStatus;
import hh.plus.server.outbox.domain.entity.Outbox;
import hh.plus.server.outbox.domain.repository.OutboxJpaRepository;
import hh.plus.server.outbox.domain.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class OutboxRepositoryImpl implements OutboxRepository {

    private final OutboxJpaRepository outboxJpaRepository;

    @Override
    public Outbox save(Outbox outbox) { return outboxJpaRepository.save(outbox); }

    @Override
    public Optional<Outbox> findById(Long outboxId) { return outboxJpaRepository.findById(outboxId); }

    @Override
    public List<Outbox> findByStatusAndCreatedAtBetween(OutboxStatus status, LocalDateTime startDate, LocalDateTime endDate) {
        return outboxJpaRepository.findByStatusAndCreatedAtBetween(status, startDate, endDate);
    }
}
