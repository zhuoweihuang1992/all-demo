package com.alog.dataservice;

import com.alog.dataservice.gateway.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class DataserviceApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataserviceApiGatewayApplication.class, args);
    }

    @Bean
    public AuthFilter authFilter(){
        return new AuthFilter();
    }
}

