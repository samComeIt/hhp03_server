package hh.plus.server.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = { "test-topic" })
public class KafkaIntegrationTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final CountDownLatch latch = new CountDownLatch(1);
    private String receivedMessage;

    @Test
    @DisplayName("테스트컨테이너로 카프카 메시지 발행 및 소비에 성공 테스트")
    public void testKafkaProducerConsumer() throws InterruptedException {
        // given
        String data = "Kafka producer sent";

        // Configure consumer properties
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // Create and configure the consumer factory
        ConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(consumerProps);

        // Create a Kafka consumer with a custom listener to handle the message
        ConcurrentMessageListenerContainer<String, String> container = new ConcurrentMessageListenerContainer<>(
                consumerFactory, new ContainerProperties("test-topic")
        );

        container.getContainerProperties().setMessageListener((MessageListener<String, String>) record -> {
            receivedMessage = record.value();
            // Signal that a message has been received
            latch.countDown();
        });

        // Start the consumer
        container.start();

        try {
            // when
            kafkaTemplate.send("test-topic", "test-key", data);

            // Wait for the message to be received
            boolean received = latch.await(10, TimeUnit.SECONDS);

            // then
            assertTrue(received, "Message should be received(within the timeout)");
            assertNotNull(receivedMessage, "Received message(must not be null)");
        } finally {
            // Stop the container after the test
            container.stop();
        }
    }

    @Test
    @DisplayName("카프카 메시지 발행 및 소비에 성공 테스트")
    public void testPublishedAndConsumedSuccess() throws Exception {
        // given
        String data = "value";

        String topic = "test-topic";
        CountDownLatch latch = new CountDownLatch(1);

        // Create a KafkaConsumer to consume messages
        Properties consumerProps = new Properties();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(Collections.singletonList(topic));

        // Send a message
        kafkaTemplate.send(topic, "key", data);

        // Consume the message
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));

        // Close consumer
        consumer.close();

        for (ConsumerRecord<String, String> record : records) {
            assertEquals(data, record.value());
        }
    }

}


