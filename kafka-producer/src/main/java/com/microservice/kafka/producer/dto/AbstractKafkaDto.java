package com.microservice.kafka.producer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractKafkaDto {
    private String topic;
}
