package com.spaishko.stringcloudstreamkafka.producer;

import org.springframework.messaging.Message;

public interface PublisherService {

    void publish(Message<?> message);

}
