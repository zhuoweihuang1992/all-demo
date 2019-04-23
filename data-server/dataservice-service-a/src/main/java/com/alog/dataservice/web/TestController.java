package com.alog.dataservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.util.Map;


@RefreshScope
@RestController
public class TestController {
    @Value("${test}")
    private String test;

    @RequestMapping("/test")
    public String test(){
        return this.test;
    }


    @Autowired
    private DiscoveryClient client;

    @PostMapping("/test")
    public String test(@RequestHeader("token") String token,@RequestBody Map<String,String> loginUser) throws UnsupportedEncodingException {
        ServiceInstance instance = client.getLocalServiceInstance();
        return instance.getServiceId();
    }
}
