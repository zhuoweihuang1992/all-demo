package com.alog.dataservice.service;


import com.alog.dataservice.entity.User;

public interface UserService {
    User findByUserName(String username);
}
