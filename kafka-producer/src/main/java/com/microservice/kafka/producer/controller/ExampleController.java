package com.microservice.kafka.producer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.kafka.producer.common.ApiCommon;
import com.microservice.kafka.producer.dto.KafkaMessageDto;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ApiCommon.API_V1_URI + ApiCommon.MESSAGE_URI)
public class ExampleController {
//    @Autowired
//    private ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(ApiCommon.CREATE_URI)
    public String createMessage(@RequestBody KafkaMessageDto kafkaMessageDto) throws Exception {
        String payload = objectMapper.writeValueAsString(kafkaMessageDto);

        String key = UUID.randomUUID().toString();
        ProducerRecord<String, String> request = new ProducerRecord<>(kafkaMessageDto.getTopic(), key, payload);
//        request.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, "reply_topic".getBytes()));

//        RequestReplyFuture<String, String, String> replyFuture = replyingKafkaTemplate.sendAndReceive(request);

//        ConsumerRecord<String, String> consumerRecord = replyFuture.get(60, TimeUnit.SECONDS);
//        return consumerRecord.value();
        this.kafkaTemplate.send(request);
        return "call successfully!";
    }

    @GetMapping(ApiCommon.LIST_URI + ApiCommon.NONE_URI + ApiCommon.TOPIC_PATH_VARIABLE)
    public String getListMessage(@PathVariable(name = "topic") String topic) throws Exception {
        KafkaMessageDto kafkaMessageDto = new KafkaMessageDto();
        kafkaMessageDto.setTopic(topic);
        kafkaMessageDto.setType("list");

        String payload = objectMapper.writeValueAsString(kafkaMessageDto);

        String key = UUID.randomUUID().toString();
        ProducerRecord<String, String> request = new ProducerRecord<>(kafkaMessageDto.getTopic(), key, payload);
//        request.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, "reply_topic".getBytes()));

//        RequestReplyFuture<String, String, String> replyFuture = replyingKafkaTemplate.sendAndReceive(request);
//        ConsumerRecord<String, String> consumerRecord = replyFuture.get();
//        List<KafkaMessageDto> messages = objectMapper.readValue(
//                consumerRecord.value(),
//                new TypeReference<List<KafkaMessageDto>>() {}
//        );

//        Map<String, Object> response = new HashMap<>();
//        response.put("status", "Message sent");
//        response.put("topic", consumerRecord.topic());
//        response.put("partition", consumerRecord.partition());
//        response.put("offset", consumerRecord.offset());
//        response.put("timestamp", consumerRecord.timestamp());
////        response.put("value", objectMapper.readValue(consumerRecord.value(), KafkaMessageDto.class));
//        response.put("value", messages);
//        return objectMapper.writeValueAsString(response);
        this.kafkaTemplate.send(request);
        return "call successfully!";
    }

}
