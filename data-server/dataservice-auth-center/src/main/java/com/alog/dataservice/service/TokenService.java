package com.alog.dataservice.service;


import com.alog.dataservice.entity.User;

public interface TokenService {
    String getToken(User user);
    String findTokenByName(String name);
}

