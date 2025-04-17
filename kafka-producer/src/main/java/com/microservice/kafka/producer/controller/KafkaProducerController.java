package com.microservice.kafka.producer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.kafka.producer.dto.KafkaMessageDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/message")
public class KafkaProducerController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/create")
    public String createMessage(@RequestBody KafkaMessageDto kafkaMessageDto) throws Exception {
        String payload = objectMapper.writeValueAsString(kafkaMessageDto);

        ProducerRecord<String, String> request = new ProducerRecord<>(kafkaMessageDto.getTopic(), payload);
        request.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, "reply_topic".getBytes()));

        RequestReplyFuture<String, String, String> replyFuture = replyingKafkaTemplate.sendAndReceive(request);

        ConsumerRecord<String, String> consumerRecord = replyFuture.get(60, TimeUnit.SECONDS);
        return consumerRecord.value();
    }

    @GetMapping("/list/{topic}")
    public String getListMessage(@PathVariable(name = "topic") String topic) throws Exception {
        // Tạo message DTO
        KafkaMessageDto kafkaMessageDto = new KafkaMessageDto();
        kafkaMessageDto.setTopic(topic);
        kafkaMessageDto.setType("list");

        String payload = objectMapper.writeValueAsString(kafkaMessageDto);

        ProducerRecord<String, String> request = new ProducerRecord<>(kafkaMessageDto.getTopic(), payload);
        request.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, "reply_topic".getBytes()));

        RequestReplyFuture<String, String, String> replyFuture = replyingKafkaTemplate.sendAndReceive(request);
        ConsumerRecord<String, String> consumerRecord = replyFuture.get(60, TimeUnit.SECONDS);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "Message sent");
        response.put("topic", consumerRecord.topic());
        response.put("partition", consumerRecord.partition());
        response.put("offset", consumerRecord.offset());
        response.put("timestamp", consumerRecord.timestamp());
        response.put("value", consumerRecord.value());
        return objectMapper.writeValueAsString(response);
    }

}
