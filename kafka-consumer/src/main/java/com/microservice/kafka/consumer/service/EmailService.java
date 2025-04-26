package com.microservice.kafka.consumer.service;

import com.microservice.kafka.consumer.dto.MessageDto;

/**
 * EmailService
 *
 * @author haoln
 * @version 01-00
 * @since 4/24/2025
 */
public interface EmailService {
    void sendMail(MessageDto messageDto);
}
