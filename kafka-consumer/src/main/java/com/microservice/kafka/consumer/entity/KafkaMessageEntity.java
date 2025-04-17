package com.microservice.kafka.consumer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "kafka_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Hoặc GenerationType.AUTO tùy theo DB
    @Column(name = "id")
    private Long id;

    @Column(name = "message")
    private String message;
}

