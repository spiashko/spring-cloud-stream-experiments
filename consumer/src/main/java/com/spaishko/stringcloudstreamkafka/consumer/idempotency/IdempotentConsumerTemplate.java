package com.spaishko.stringcloudstreamkafka.consumer.idempotency;

import com.spaishko.stringcloudstreamkafka.consumer.messagelog.MessageLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.function.Consumer;

@Component
@SuppressWarnings({"ClassCanBeRecord"})
@Slf4j
@RequiredArgsConstructor
public class IdempotentConsumerTemplate {

    private final MessageLogService messageLogService;

    @Transactional
    public <T> void process(Message<T> message, Consumer<Message<T>> consumer) {
        UUID eventId = message.getHeaders().get("x-message-id", UUID.class);
        if (messageLogService.alreadyProcessed(eventId)) {
            log.info("Event with UUID {} was already retrieved, ignoring it", eventId);
            return;
        }

        consumer.accept(message);

        messageLogService.processed(eventId);
    }
}
