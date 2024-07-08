package hh.plus.server.balance.domain.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

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

    public Balance(Long balanceId, Long balance, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.balanceId = balanceId;
        this.balance = balance;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Long getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Long balanceId) {
        this.balanceId = balanceId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void newBalance(Long amount)
    {
        if(this.balance + amount < 0) throw new RuntimeException("Cannot be minus");

        this.setBalance(this.balance + amount);
    }
}
