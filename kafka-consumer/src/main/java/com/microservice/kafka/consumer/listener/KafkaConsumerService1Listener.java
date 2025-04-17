package com.microservice.kafka.consumer.listener;

import org.springframework.stereotype.Component;

@Component
public interface KafkaConsumerService1Listener {
    String listener(String value) throws Exception;
}
