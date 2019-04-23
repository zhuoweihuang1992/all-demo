package com.alog.dataservice.service;

import com.alog.dataservice.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service("TokenService")
public class TokenService {
    public String getToken(User user) {
        String uuid = UUID.randomUUID().toString();
        String token="";
       /* token= JWT.create().withAudience(uuid)
                // 将 uuid 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;*/
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512,user.getPassword())
                //.setClaims(map)
                //.setIssuer(ISS)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                //.setExpiration(new Date(System.currentTimeMillis()+expiration * 1000))
                .compact();
    }
}
