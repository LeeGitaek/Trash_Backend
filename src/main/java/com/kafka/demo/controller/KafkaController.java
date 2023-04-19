package com.kafka.demo.controller;

import com.kafka.demo.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final KafkaProducer producer;

    @Autowired
    KafkaController(KafkaProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/test")
    public String kafkaTest() {
        return "hello kafka!";
    }

    @GetMapping
    public String sendMessage(@RequestParam(value = "message") String message) {
        this.producer.sendMessage(message);

        return "success";
    }
}