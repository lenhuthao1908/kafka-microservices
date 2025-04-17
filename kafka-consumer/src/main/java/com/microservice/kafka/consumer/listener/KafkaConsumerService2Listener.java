package com.microservice.kafka.consumer.listener;

public interface KafkaConsumerService2Listener {
    String listener(String value) throws Exception;
}
