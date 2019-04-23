package com.alog.dataservice.web;

import com.alog.dataservice.entity.User;
import com.alog.dataservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public int addUser( @RequestBody User user ) {
        return userService.addUser( user );
    }
    /*@RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public int addUser( @RequestParam long userId,@RequestParam String username,@RequestParam boolean sex,@RequestParam String createdTime ) {
        User user = new User();
        user.setUserId(userId);
        user.setUserName(username);
        user.setSex(sex);
        user.setCreatedTime(createdTime);
        return userService.addUser( user );
    }*/

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public int deleteUser( @RequestBody User user ) {
        return userService.deleteUser( user );
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(@RequestParam Integer a,@RequestParam Integer b){

        return "service-b";
    }

}
