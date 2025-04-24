package com.microservice.kafka.consumer.service;

import com.microservice.kafka.consumer.dto.MessageDto;

/**
 * EmailConsumerService
 *
 * @author haoln
 * @version 01-00
 * @since 4/24/2025
 */
public interface EmailConsumerService {
    void sendMail(MessageDto messageDto);
}
