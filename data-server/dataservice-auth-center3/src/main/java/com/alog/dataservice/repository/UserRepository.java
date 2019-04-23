package com.alog.dataservice.repository;

import com.alog.dataservice.entity.RegisterUser;
import com.alog.dataservice.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


public interface UserRepository extends CrudRepository<RegisterUser,Integer> {
    User findByUsername(String username);
}
