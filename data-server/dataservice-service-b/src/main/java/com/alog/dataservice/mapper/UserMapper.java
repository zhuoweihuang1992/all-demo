package com.alog.dataservice.mapper;

import com.alog.dataservice.entity.User;

import java.util.List;

public interface UserMapper {
    List<User> getAllUsers();
    int addUser(User user);
    int deleteUser(User user);
}
