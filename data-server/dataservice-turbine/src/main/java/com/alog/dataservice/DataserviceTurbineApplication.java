package com.alog.dataservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class DataserviceTurbineApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DataserviceTurbineApplication .class).web(true).run(args);
    }

}

