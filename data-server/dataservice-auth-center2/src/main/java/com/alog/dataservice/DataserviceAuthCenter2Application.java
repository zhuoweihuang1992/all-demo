package com.alog.dataservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.alog.dataservice.mapper")
public class DataserviceAuthCenter2Application {

    public static void main(String[] args) {
        SpringApplication.run(DataserviceAuthCenter2Application.class, args);
    }

}