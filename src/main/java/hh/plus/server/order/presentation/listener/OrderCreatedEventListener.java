package hh.plus.server.order.presentation.listener;

import hh.plus.server.common.service.KafkaProducerService;
import hh.plus.server.order.domain.listener.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventListener {
    private KafkaProducerService kafkaProducerService;
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Order created with ID: " + event.getOrderId());
        kafkaProducerService.sendMessage("order-topic", String.valueOf(event.getOrderId()));

    }
}
