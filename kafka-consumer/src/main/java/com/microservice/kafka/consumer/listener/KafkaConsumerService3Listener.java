package com.microservice.kafka.consumer.listener;

public interface KafkaConsumerService3Listener {
    String listener(String value) throws Exception;
}
