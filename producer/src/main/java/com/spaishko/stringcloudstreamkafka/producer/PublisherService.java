package com.spaishko.stringcloudstreamkafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.Publisher;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@EnableBinding(Bindings.class)
@Service
public class PublisherService {

    private final LocalDateTime startDate = LocalDateTime.now();

    private long counter = 1L;

    @Publisher(Bindings.OUTPUT)
    public Message<?> queue() {

        String messageId = UUID.randomUUID().toString();
        long producingTime = new Date().getTime();

        String body = startDate.withNano(0) + " " + counter + " " + messageId + " " + producingTime;

        Message<String> build = MessageBuilder
                .withPayload(body)
                .setHeader("x-message-id", messageId)
                .setHeader("x-message-timestamp", producingTime)
                .build();

        counter++;
        return build;
    }

}