package com.alog.dataservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired  
    private LoadBalancerClient loadBalancerClient;  

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@RequestParam Integer a,@RequestParam Integer b) {
        System.out.println("进入ribbon");
    	this.loadBalancerClient.choose("service-c");//随机访问策略
        return restTemplate.getForEntity("http://service-c/add?a="+a+"&b="+b, String.class).getBody();
        /**
         * 200 * 1.98  396
         * 396 * 1.98  784
         * 784 * 1.98  1552
         * 1552 * 1.98  3072
         * 3072 * 1.98  6084
         * 6084 * 1.98  12047
         * 12047 * 1.98  23853
         * 23853 * 1.98  47229
         * 47229 * 1.98  93513
         * 93513 * 1.98  185155
         * 185155 / 5 37031
         *
         * 200 * 2.1  420
         * 420 * 2.1  882
         * 882 * 2.1  1852
         * 1852 * 2.1 3889
         * 3889 * 2.1 8168
         * 8168 * 2.1 17153
         * 17153 * 2.1 36021
         * 36021 * 2.1 75644
         * 75644 * 2.1 158853
         * 158853 * 2.1 333593
         * 333593 / 5 66718.6
         *
         * 500 * 1.98 990  --490
         * 1000 * 2.1 2100 --1100
         * 2000 * 2.1 4200 --2200
         * 5000 * 2.1 10500 --5500
         * 1000 * 9  9000 --8000
         * 2000 * 1.98 3960 --1960
         * 5000 * 2.1 10500 --55000
         * 20000 * 2.1 42000 --22000
         * 20000 * 2.1 42000 --22000
         * 20000 * 9.0 180000 --160000
         * 270000 * 2.1 567000 --297000
         * 574250 / 5 114850
         *
         *
         *
         *
         *
         *
         *
         *
         */
    	
    }
    
}