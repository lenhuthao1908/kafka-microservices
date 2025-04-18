package com.microservice.kafka.consumer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KafkaMessageDto extends AbstractKafkaDto {
    private String message;
    private String type;
}
