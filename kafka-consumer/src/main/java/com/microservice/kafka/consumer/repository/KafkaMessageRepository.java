package com.microservice.kafka.consumer.repository;

import com.microservice.kafka.consumer.entity.KafkaMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaMessageRepository extends JpaRepository<KafkaMessageEntity, Long> {
}
