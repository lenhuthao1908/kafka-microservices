package com.microservice.kafka.consumer.listener;

public interface KafkaConsumerService4Listener {
    String listener(String value) throws Exception;
}
