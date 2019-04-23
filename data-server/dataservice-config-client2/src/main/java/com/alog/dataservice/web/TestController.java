package com.alog.dataservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {
    @Value("${form}")
    private String form;

    @Autowired
    private Environment environment;
    @GetMapping("/get_name")
    public String name(){
        return "form:"+form;
    }
    @GetMapping("/get_name_env")
    public String name_env(){
        return environment.getProperty("form","undefine");
    }


}
