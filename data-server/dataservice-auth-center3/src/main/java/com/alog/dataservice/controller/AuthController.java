package com.alog.dataservice.controller;

import com.alog.dataservice.entity.RegisterUser;
import com.alog.dataservice.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody Map<String,String> userinfo){
        RegisterUser user = new RegisterUser();
        user.setUsername(userinfo.get("username"));
        String pwt = BCrypt.hashpw(userinfo.get("password"), BCrypt.gensalt());
        System.out.println("加密后的密码："+pwt);
        user.setPassword(pwt);
        //userService.save(user);
        userRepository.save(user);
        return "保存成功！";
    }

}
