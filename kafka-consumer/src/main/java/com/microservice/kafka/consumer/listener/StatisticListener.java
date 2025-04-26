package com.microservice.kafka.consumer.listener;

/**
 * StatisticListener
 *
 * @author haoln
 * @version 01-00
 * @since 4/26/2025
 */
public interface StatisticListener {
    void listener(String value) throws Exception;
}
