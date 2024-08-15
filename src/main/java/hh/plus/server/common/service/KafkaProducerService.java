package hh.plus.server.common.service;

import hh.plus.server.outbox.service.OutboxService;
import hh.plus.server.outbox.service.dto.OutboxDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OutboxService outboxService;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);

        // outbox 데이터 INIT 생성
        OutboxDto outboxDto = new OutboxDto(message);
        outboxService.add(outboxDto);
    }
}
