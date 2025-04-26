package com.microservice.kafka.consumer.service.impl;

import com.microservice.kafka.consumer.dto.MessageDto;
import com.microservice.kafka.consumer.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

/**
 * EmailServiceImpl
 *
 * @author haoln
 * @version 01-00
 * @since 4/24/2025
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    @Async
    public void sendMail(MessageDto messageDto) {
        try {
            log.info("[START] - sendMail: " + messageDto.toString() + " sending email");
            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, StandardCharsets.UTF_8.name());

            /*load template email with content*/
            Context context = new Context();
            context.setVariable("name", messageDto.getToName());
            context.setVariable("content", messageDto.getContent());
            String html = templateEngine.process("welcome-email", context);

            /*send email*/
            mimeMessageHelper.setTo(messageDto.getTo());
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject(messageDto.getSubject());
            mimeMessageHelper.setFrom(from);
            javaMailSender.send(mimeMailMessage);
            log.info("[END] - sendMail success");
        } catch (Exception e) {
            log.error("email sent with error: " + e.getMessage() );
        }

    }
}
