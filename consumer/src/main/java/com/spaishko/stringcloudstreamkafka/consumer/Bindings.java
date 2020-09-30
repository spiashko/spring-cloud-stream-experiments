package com.spaishko.stringcloudstreamkafka.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Bindings {

    String INPUT = "myinput";

    @Input(Bindings.INPUT)
    SubscribableChannel input();

}