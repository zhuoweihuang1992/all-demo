package com.alog.dataservice.controller;

import com.alog.dataservice.config.RedisService;
import com.alog.dataservice.config.UserVo;
import com.alog.dataservice.entity.User;
import com.alog.dataservice.entity.UserToken;
import com.alog.dataservice.service.TokenService;
import com.alog.dataservice.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Resource
    private ValueOperations<String,Object> valueOperations;

    @Resource
    private RedisService redisService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody Map<String,String> loginUser){
        User user = new User();
        user.setUsername(loginUser.get("username"));
        User us = userService.findByUsername(user);
        if(us != null){
            boolean pswFlag = BCrypt.checkpw(loginUser.get("password"),us.getPassword());
            if(pswFlag){
                user.setUsername(us.getUsername());
                user.setPassword(loginUser.get("password"));
                String token = tokenService.getToken(user);
                //将token保存到redis并设置有效时间
                UserToken userToken = new UserToken();
                userToken.setId(us.getId());
                userToken.setUsername(us.getUsername());
                userToken.setPassword(us.getPassword());
                userToken.setToken(token);
                valueOperations.set(loginUser.get("username"),userToken);
                redisService.expireKey(loginUser.get("username"),60*60*24, TimeUnit.SECONDS);
                return token;
            }
        }else{
            return "登录失败！";
        }
        return null;
    }
}
