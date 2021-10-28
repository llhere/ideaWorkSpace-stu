package com.chen.cloud.microservicesimpleprovideruser.controller;

import com.chen.cloud.microservicesimpleprovideruser.domain.User;
import com.chen.cloud.microservicesimpleprovideruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 根据id查询User实体
     * @param id
     * @return
     */
    @GetMapping("/simple/{id}")
    public User findUserById(@PathVariable Long id){

        return userService.findUserById(id);
    }

    @PostMapping("user")
    public User postUser(@RequestBody  User user){
        return user;
    }

}
