package com.microservice.kafka.consumer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * MessageDto
 *
 * @author haoln
 * @version 01-00
 * @since 4/24/2025
 */
@Getter
@Setter
@Builder
public class MessageDto {
    private String to;
    private String toName;
    private String subject;
    private String content;
}
