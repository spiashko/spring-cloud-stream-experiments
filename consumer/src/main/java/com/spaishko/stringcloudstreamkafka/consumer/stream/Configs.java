package com.spaishko.stringcloudstreamkafka.consumer.stream;

import com.spaishko.stringcloudstreamkafka.consumer.idempotency.IdempotentConsumerTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class Configs {

    @Bean
    Consumer<Message<MessageModel>> dataConsumer(IdempotentConsumerTemplate idempotentConsumerTemplate,
                                                 ConsumerService consumerService) {
        return (message) -> idempotentConsumerTemplate.process(message, consumerService::accept);
    }

}
