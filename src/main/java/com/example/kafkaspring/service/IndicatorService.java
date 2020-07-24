package com.example.kafkaspring.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;


@Service
public class IndicatorService {

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    /**
     * 消费者
     * @param record
     */
    @KafkaListener(topics = "topic1", groupId = "aaa")
    public void processMessage(ConsumerRecord<Integer, String> record) {
        System.out.println("kafka processMessage start");
        System.out.println("processMessage, topic = {"+record.topic()+"}, msg = "+ record.value());

        // do something ...

        System.out.println("kafka processMessage end");
    }

    /**
     * @param topic
     * @param data
     */
    public void sendMessage(String topic, String data) {
        System.out.println("kafka sendMessage start");

        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("kafka sendMessage error, ex = {"+ex+"}, topic = {"+topic+"}, data = {"+data+"}");
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                System.out.println("kafka sendMessage success topic = {"+topic+"}, data = {"+data+"}");
            }
        });

        System.out.println("kafka sendMessage end");
    }
}
