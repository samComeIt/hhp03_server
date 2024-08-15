package hh.plus.server.outbox.domain.entity;

import hh.plus.server.outbox.domain.OutboxStatus;
import hh.plus.server.outbox.domain.OutboxType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Outbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long outboxId;

    private String id;

    private OutboxType type;

    private String message;

    private OutboxStatus status;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Outbox(String id, OutboxType type, String message, OutboxStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public Long getOutboxId() {
        return outboxId;
    }

    public String getMessage() {
        return message;
    }

    public OutboxType getType() {
        return type;
    }

    public OutboxStatus getStatus() {
        return status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void newStatus(OutboxStatus status) {
        if (this.status == status) throw new RuntimeException("Status no need to be updated");

        this.status = status;
        changeUpatedAt();
    }

    public void changeUpatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
