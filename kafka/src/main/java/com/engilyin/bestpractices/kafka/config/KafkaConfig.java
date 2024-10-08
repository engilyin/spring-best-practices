package com.engilyin.bestpractices.kafka.config;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.engilyin.bestpractices.kafka.data.MyMessage;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableConfigurationProperties({KafkaProperties.class})
@RequiredArgsConstructor
public class KafkaConfig {
    
    private final KafkaProperties kafkaProperties;
    
    private final SslBundles sslBundles;
    
    @Bean
    public ProducerFactory<String, MyMessage> producerFactory() {
	return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties(sslBundles));
    }
    
    @Bean
    public KafkaTemplate<String, MyMessage> myKafkaTemplate() {
	return new KafkaTemplate<>(producerFactory());
    }
}
