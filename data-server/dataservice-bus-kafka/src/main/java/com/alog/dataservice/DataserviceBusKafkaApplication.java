package com.alog.dataservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DataserviceBusKafkaApplication {
    @Autowired
    private KafkaSender kafkaSender;

    @PostConstruct
    public void init(){
        for (int i = 0; i < 10; i++) {
            //调用消息发送类中的消息发送方法
            kafkaSender.send((long) i);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DataserviceBusKafkaApplication.class, args);
    }

}

