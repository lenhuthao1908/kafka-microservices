package com.microservice.kafka.consumer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaMessageDto extends AbstractKafkaDto {
    private String message;
    private String type;
}
