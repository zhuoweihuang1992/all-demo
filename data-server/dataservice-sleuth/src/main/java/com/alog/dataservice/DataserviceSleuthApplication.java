package com.alog.dataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class DataserviceSleuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataserviceSleuthApplication.class, args);
    }

}

