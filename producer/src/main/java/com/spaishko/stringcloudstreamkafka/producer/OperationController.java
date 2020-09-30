package com.spaishko.stringcloudstreamkafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class OperationController {

    private final PublisherService publisherService;


    @GetMapping("/produce")
    public String produce(@RequestParam(name = "count", required = false, defaultValue = "50") Integer count) {

        for (int i = 0; i < count; i++) {
            publisherService.queue();
        }

        return "ok";
    }

}
