package com.engilyin.bestpractices.kafka.producers;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.common.errors.SerializationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.engilyin.bestpractices.kafka.data.MyMessage;
import com.engilyin.bestpractices.kafka.exceptions.SendException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyProducer {

    private final String topic;

    private final KafkaTemplate<String, MyMessage> kafkaTemplate;

    public MyProducer(@Value("${app.kafka.topic.my}") String topic, KafkaTemplate<String, MyMessage> kafkaTemplate) {
	this.topic = topic;
	this.kafkaTemplate = kafkaTemplate;
    }

    public void send(MyMessage myMessage) {

	log.debug("Sending the message {}", myMessage);

	try {

	    SendResult<String, MyMessage> sr = kafkaTemplate.send(topic, myMessage).get();

	    log.debug("Produced records: {} Record metadata: {}", sr.getProducerRecord(), sr.getRecordMetadata());

	} catch (KafkaException | InterruptedException | ExecutionException | SerializationException e) {
	    var errorMessage = "I can't send my message";
	    log.error(errorMessage, e);
	    throw new SendException(errorMessage, e);
	}
    }
}
