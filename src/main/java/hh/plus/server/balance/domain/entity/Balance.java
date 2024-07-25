package hh.plus.server.balance.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "balance", indexes = {
        @Index(name="idx_balance", columnList = "balanceId")
})
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long balanceId;

    private Long balance;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    @Version
    private Long version; // for optimistic locking

    @Builder
    public Balance(Long balanceId, Long balance, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.balanceId = balanceId;
        this.balance = balance;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Long getBalanceId() {
        return balanceId;
    }

    public Long getBalance() {
        return balance;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void newBalance(Long amount)
    {
        if(this.balance + amount < 0) throw new RuntimeException("Cannot be minus");

        this.balance += amount;
        changeUpdatedAt();
    }


    public void changeUpdatedAt()
    {
        this.updatedAt = LocalDateTime.now();
    }
}
