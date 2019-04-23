package com.alog.dataservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthTestController {
    @RequestMapping("/authTest")
    public ResponseEntity<String> test(HttpServletRequest request){
        String Authorization = "BearereyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ6aHVvd2VpaHVhbmciLCJpc3MiOiJ6aHVvd2VpaHVhbmciLCJleHAiOjE1NTE3NTc1NDksImlhdCI6MTU1MTE1Mjc0OSwicm9sIjoiUk9MRV9BRE1JTiJ9.r2cXTJakn7f3FSNUlFbsyVm5w0ieOj4l-CW0ldMXxxf5fyxujhTlLnnUNftv4Dacie85pcVXrtlQnWglDy4-jA";
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8102/tasks";
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("username","zhuoweihuang");
        map.add("password","zhuoweihuang");
        return restTemplate.postForEntity(url, map,String.class);
    }
}
