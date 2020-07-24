package com.example.kafkaspring.controller;

import com.example.kafkaspring.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    @Autowired
    IndicatorService indicatorService;

    @RequestMapping("/send")
    public String send(@RequestParam("topic") String topic,@RequestParam("message") String message){
        indicatorService.sendMessage(topic,message);
        return "success!";
    }


}
