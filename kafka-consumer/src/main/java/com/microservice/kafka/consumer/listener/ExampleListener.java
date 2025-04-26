package com.microservice.kafka.consumer.listener;

import org.springframework.stereotype.Component;

@Component
public interface ExampleListener {
    String listener(String value) throws Exception;
}
