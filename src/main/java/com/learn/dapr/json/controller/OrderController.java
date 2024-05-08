package com.learn.dapr.json.controller;

import com.learn.dapr.json.model.OrderRequest;
import com.learn.dapr.json.service.JsonPublishEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    //    private static final String TOPIC="orders";
//    private static final String PUBSUB_NAME="";
    private final JsonPublishEventService daprPublishEventService;

    @GetMapping("/ping")
    public String ping() {
        return "ping...";
    }

    @PostMapping("/orders/publish")
    public String publishEventDapr(@RequestBody OrderRequest orderRequest) throws InterruptedException {
        daprPublishEventService.publishEventUsingMetadata(orderRequest.getPubsubName(), orderRequest.getTopic(), orderRequest.getOrder());

        log.info("Published data: " + orderRequest.getOrder());
//        TimeUnit.MILLISECONDS.sleep(1000);
        return "Published";
    }
}
