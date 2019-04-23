package com.alog.dataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DataserviceEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataserviceEurekaServerApplication.class, args);
    }

}

