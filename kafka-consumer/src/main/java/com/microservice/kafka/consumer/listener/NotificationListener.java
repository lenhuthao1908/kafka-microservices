package com.microservice.kafka.consumer.listener;

/**
 * MessageListener
 *
 * @author haoln
 * @version 01-00
 * @since 4/26/2025
 */
public interface NotificationListener {
    void listener(String value) throws Exception;
}
