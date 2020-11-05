package com.spaishko.stringcloudstreamkafka.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Bindings {

    String INPUT = "myinput";

    String DL_INPUT = "mydlinput";

    @Input(Bindings.INPUT)
    SubscribableChannel input();

    @Input(Bindings.DL_INPUT)
    SubscribableChannel dlinput();

}