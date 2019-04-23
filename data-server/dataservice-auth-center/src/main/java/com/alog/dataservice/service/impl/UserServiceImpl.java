package com.alog.dataservice.service.impl;


import com.alog.dataservice.entity.User;
import com.alog.dataservice.repository.UserRepository;
import com.alog.dataservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    public User findByUserName(String username) {
        User user = userRepository.findByUsername(username);
        return user;

    }
}
