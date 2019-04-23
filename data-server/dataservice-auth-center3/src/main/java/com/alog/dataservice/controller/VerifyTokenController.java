package com.alog.dataservice.controller;

import com.alog.dataservice.entity.User;
import com.alog.dataservice.entity.UserToken;
import com.alog.dataservice.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class VerifyTokenController {

    @Autowired
    private UserService userService;

    @Resource
    private ValueOperations<String,Object> valueOperations;

    //返回0表示成功，返回1表示失败
    @RequestMapping("/verifyToken")
    public int verifyToken(HttpServletRequest request,@RequestBody Map<String,String> loginUser){
        User user = new User();
        user.setUsername(loginUser.get("username"));
        user.setPassword(loginUser.get("password"));
        //user.setUserauth(loginUser.get("userauth"));

        String token = request.getHeader("token");
        String serviceId = request.getHeader("serviceId");
        String userauth =  request.getHeader("userauth");
        //从redis查询该token
        UserToken userToken = new UserToken();
        userToken = (UserToken) valueOperations.get(loginUser.get("username"));
        try{
            if(userToken.getToken() == null){
                return 1;
            }else{
                if(token.equals(userToken.getToken())){
                    // 验证 token
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                    try {
                        jwtVerifier.verify(token);
                    } catch (JWTVerificationException e) {
                        throw new RuntimeException("401");
                    }
                    User byUser = userService.findByUsername(user);
                    byUser.getUserauth();
                    if(serviceId == null && byUser.getUserauth().equals(userauth)){
                        return 0;
                    }else{
                        if(serviceId.equals(byUser.getUserauth())){
                            return 0;
                        }
                    }
                }
            }
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
       return 1;
    }
}
