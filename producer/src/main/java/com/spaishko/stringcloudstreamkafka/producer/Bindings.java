package com.spaishko.stringcloudstreamkafka.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Bindings {

    String OUTPUT = "myoutput";

    @Output(Bindings.OUTPUT)
    MessageChannel output();
}