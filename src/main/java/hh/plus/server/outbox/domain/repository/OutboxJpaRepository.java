package hh.plus.server.outbox.domain.repository;

import hh.plus.server.outbox.domain.OutboxStatus;
import hh.plus.server.outbox.domain.entity.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OutboxJpaRepository extends JpaRepository<Outbox, Long> {
    Outbox save(Outbox outbox);

    Optional<Outbox> findById(Long outboxId);

    @Query("SELECT o FROM Outbox o WHERE o.status = :status AND o.createdAt BETWEEN :startDate AND :endDate")
    List<Outbox> findByStatusAndCreatedAtBetween(
            @Param("status") OutboxStatus status,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

}
