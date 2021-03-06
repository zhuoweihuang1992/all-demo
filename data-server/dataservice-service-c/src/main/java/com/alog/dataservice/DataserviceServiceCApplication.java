package com.alog.dataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class DataserviceServiceCApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataserviceServiceCApplication.class, args);
    }

}

