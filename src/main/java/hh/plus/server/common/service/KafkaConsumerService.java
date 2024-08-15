package hh.plus.server.common.service;

import hh.plus.server.outbox.domain.entity.Outbox;
import hh.plus.server.outbox.domain.repository.OutboxRepository;
import lombok.NoArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private OutboxRepository outboxRepository;

    @KafkaListener(topics = "my-topic", groupId = "ecommerce-group")
    public void listen(String message) {
        updateOutboxStatus(message);
    }

    public void updateOutboxStatus(String message) {
        //Outbox outbox = outboxRepo
    }
}
