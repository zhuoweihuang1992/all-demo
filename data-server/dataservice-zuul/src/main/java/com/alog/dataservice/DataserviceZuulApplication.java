package com.alog.dataservice;

import com.alog.dataservice.filter.ErrorFilter;
import com.alog.dataservice.filter.ResultFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringCloudApplication
public class DataserviceZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataserviceZuulApplication.class, args);
    }

    @Bean
    public ResultFilter resultFilter() {
        return new ResultFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

}

