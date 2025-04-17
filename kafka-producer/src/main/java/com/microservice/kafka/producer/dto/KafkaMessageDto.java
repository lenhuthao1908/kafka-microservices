package com.microservice.kafka.producer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaMessageDto extends AbstractKafkaDto {
    private String message;
    private String type;
}
