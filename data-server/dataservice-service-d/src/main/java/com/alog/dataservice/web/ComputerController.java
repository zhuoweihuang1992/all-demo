package com.alog.dataservice.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ComputerController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(@RequestParam Integer a,@RequestParam Integer b){
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add,host:"+instance.getHost()+",service_id:"+instance.getServiceId() + "port:"+instance.getPort()+", result:" + r);
        return "From Seervice-a,port is "+instance.getPort()+",Result is " + r;
    }

    //A服务调用B服务
    @RequestMapping(value = "/testServiceC",method = RequestMethod.GET)
    public String testServiceB(@RequestParam Integer a,@RequestParam Integer b){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8791/add?a="+a+"&b="+b,String.class);
    }
}
