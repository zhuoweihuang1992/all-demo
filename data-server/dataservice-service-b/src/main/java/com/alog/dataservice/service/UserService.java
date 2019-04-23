package com.alog.dataservice.service;

import com.alog.dataservice.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public int addUser(User user);
    public int deleteUser(User user);

}
