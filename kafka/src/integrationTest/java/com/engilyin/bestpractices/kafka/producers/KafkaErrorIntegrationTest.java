package com.engilyin.bestpractices.kafka.producers;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.apache.kafka.common.errors.SerializationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ssl.SslAutoConfiguration;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.engilyin.bestpractices.kafka.config.KafkaConfig;
import com.engilyin.bestpractices.kafka.data.MyMessage;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SslAutoConfiguration.class, KafkaConfig.class, MyProducer.class }, initializers = {
	ConfigDataApplicationContextInitializer.class })
@EmbeddedKafka(partitions = 1, topics = { "${app.kafka.topic.my}" })
@TestPropertySource(properties = { "spring.config.location = classpath:application.yml, classpath:application-test.yml",
	"spring.kafka.producer.value-serializer = com.engilyin.bestpractices.kafka.producers.BrokenSerializer" })
public class KafkaErrorIntegrationTest {

    @Autowired
    MyProducer myProducer;

    @MockBean
    SslBundles sslBundles;

    @Test
    void failedKafkaSerialization() {
	var message = new MyMessage("1234", "test", "test");

	assertThatThrownBy(() -> myProducer.send(message)).isInstanceOf(Exception.class)
		.hasCauseInstanceOf(SerializationException.class);
    }
}
