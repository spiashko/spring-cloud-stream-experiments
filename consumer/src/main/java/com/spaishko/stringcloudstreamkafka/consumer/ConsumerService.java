package com.spaishko.stringcloudstreamkafka.consumer;

import com.spaishko.stringcloudstreamkafka.consumer.message.Message;
import com.spaishko.stringcloudstreamkafka.consumer.message.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@RequiredArgsConstructor
@EnableBinding(Bindings.class)
@Service
public class ConsumerService {

    private final MessageRepository repository;


    @ServiceActivator(inputChannel = Bindings.DL_INPUT,
            adviceChain = {"intTransactionInterceptor", "idempotentReceiverInterceptor"})
    public void dequeue(org.springframework.messaging.Message<?> message) {
        System.out.println("dead letter queue received message: " + message);
    }

    @ServiceActivator(inputChannel = Bindings.INPUT,
            adviceChain = {"intTransactionInterceptor", "idempotentReceiverInterceptor"})
    public void dequeue(String message) {
        System.out.println("received message: " + message);
        System.out.print("Throw exception? ");
        Scanner n = new Scanner(System.in);
        boolean bn = n.nextBoolean();
        if (bn) {
            System.out.println("exception will thrown!");
            throw new RuntimeException("some bad stuff happened");
        }
        repository.save(new Message(message));
    }

    @ServiceActivator(inputChannel = Configs.IDEMPOTENT_DISCARD_CHANNEL)
    public void idempotentReceiverInterceptorDequeue(String message) {
        System.out.println("second receive of message: " + message);
    }

    @ServiceActivator(inputChannel = "errorChannel")
    public void errorChannel(org.springframework.messaging.Message<?> message) {
        System.out.println("bad message: " + message);
    }

}
