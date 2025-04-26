package com.microservice.kafka.producer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Bean;

/**
 * TopicEnums
 *
 * @author haoln
 * @version 01-00
 * @since 4/24/2025
 */
@AllArgsConstructor
@Getter
public enum TopicEnums {
    SERVICE1_TOPIC("example_topic", 10, (short)1),
    NOTIFICATION_TOPIC("notification_topic", 2, (short) 1),
    STATISTIC_TOPIC("statistic_topic", 1, (short) 1),
    ;
    private String name;
    private int numPartitions;
    private short replicationFactor;

}
