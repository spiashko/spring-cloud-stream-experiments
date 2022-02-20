package com.spaishko.stringcloudstreamkafka.producer.impl;

import com.spaishko.stringcloudstreamkafka.producer.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PublisherServiceImpl implements PublisherService {

    private final StreamBridge streamBridge;

    @Override
    public void publish(Message<?> message) {
        boolean sendResult = streamBridge.send("myoutput-out-0", message);
        if (!sendResult) {
            throw new RuntimeException("there was a problem with message sending");
        }
    }

}
