package com.alog.dataservice.repository;

import com.alog.dataservice.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Integer> {
    User findByUsername(String username);
}
