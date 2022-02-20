package com.spaishko.stringcloudstreamkafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class OperationController {

    private static final LocalDateTime appStartDate = LocalDateTime.now();
    private static long appGlobalCounter = 1L;

    private final PublisherService publisherService;

    @PostMapping("/produce")
    public String produce(@RequestParam(name = "count", required = false, defaultValue = "50") Integer count) {

        for (int i = 0; i < count; i++) {
            Message<MessageModel> message = prepareMessage();
            publisherService.publish(message);
        }

        return "ok";
    }

    private Message<MessageModel> prepareMessage() {
        UUID messageId = UUID.randomUUID();
        Instant producingTime = Instant.now();

        String body = appStartDate.withNano(0) + " " + appGlobalCounter + " " + messageId + " " + producingTime;

        Message<MessageModel> message = MessageBuilder
                .withPayload(new MessageModel(body))
                .setHeader("x-message-id", messageId)
                .setHeader("x-message-timestamp", producingTime)
                .build();

        appGlobalCounter++;

        return message;
    }

}
