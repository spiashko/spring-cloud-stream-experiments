package com.spaishko.stringcloudstreamkafka.consumer.stream;

import com.spaishko.stringcloudstreamkafka.consumer.message.DataEntity;
import com.spaishko.stringcloudstreamkafka.consumer.message.DataEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {

    private final DataEntityRepository repository;

    @SneakyThrows
    public void accept(Message<MessageModel> stringMessage) {
        log.info("received message: id={} timestamp={} body={}",
                stringMessage.getHeaders().get("x-message-id", UUID.class),
                stringMessage.getHeaders().get("x-message-timestamp", Instant.class),
                stringMessage.getPayload());
        repository.save(new DataEntity(stringMessage.getPayload().getMessage()));
    }
}
