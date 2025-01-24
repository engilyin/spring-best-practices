package com.engilyin.bestpractices.kafka.producers;

import static org.assertj.core.api.Assertions.assertThat;

import com.engilyin.bestpractices.kafka.config.KafkaConfig;
import com.engilyin.bestpractices.kafka.data.MyMessage;
import java.util.List;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.autoconfigure.ssl.SslAutoConfiguration;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {SslAutoConfiguration.class, KafkaConfig.class, MyProducer.class},
        initializers = {ConfigDataApplicationContextInitializer.class})
@EmbeddedKafka(
        partitions = 1,
        topics = {"${app.kafka.topic.my}"})
@TestPropertySource(properties = {"spring.config.location = classpath:application.yml, classpath:application-test.yml"})
class MyProducerIntegrationTest {

    @Value("${app.kafka.topic.my}")
    String topic;

    @Autowired
    KafkaProperties kafkaProperties;

    @Autowired
    MyProducer myProducer;

    @MockBean
    // @Autowired
    SslBundles sslBundles;

    Consumer<String, MyMessage> consumer;

    @BeforeEach
    void setUp() {

        ConsumerFactory<String, MyMessage> consumerFactory =
                new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(sslBundles));

        this.consumer = consumerFactory.createConsumer();
        this.consumer.subscribe(List.of(topic));
    }

    @AfterEach
    void tearDown() {
        consumer.close();
    }

    @Test
    void testSend_validMessage_received() {
        var myMessage = new MyMessage("0033434", "Testing element", "Message for integration testing");

        myProducer.send(myMessage);

        ConsumerRecords<String, MyMessage> records = KafkaTestUtils.getRecords(consumer);

        assertThat(records.count()).isEqualTo(1);

        ConsumerRecord<String, MyMessage> receivedRecord = records.iterator().next();

        assertThat(receivedRecord).isNotNull();
        assertThat(receivedRecord.value()).isEqualTo(myMessage);
    }
}
