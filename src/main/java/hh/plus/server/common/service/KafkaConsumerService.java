package hh.plus.server.common.service;

import hh.plus.server.outbox.service.OutboxService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private OutboxService outboxService;

    @KafkaListener(topics = "my-topic", groupId = "ecommerce-group")
    public void listen(ConsumerRecord<String, String> record) {
        updateOutboxStatus(Long.parseLong(record.value()));
    }

    public void updateOutboxStatus(Long outboxId) {
        outboxService.update(outboxId);
        return;
    }
}
