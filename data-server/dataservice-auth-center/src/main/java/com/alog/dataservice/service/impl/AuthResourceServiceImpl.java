package com.alog.dataservice.service.impl;



import com.alog.dataservice.entity.AuthResource;
import com.alog.dataservice.repository.RoleResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthResourceServiceImpl {
    @Autowired
    private RoleResourceRepository roleResourceRepository;

    public AuthResource loadResourceByUsername(String username, String role, String url){
        AuthResource resource = roleResourceRepository.findByUsername(username,role,url);
        return resource;
    }

}
