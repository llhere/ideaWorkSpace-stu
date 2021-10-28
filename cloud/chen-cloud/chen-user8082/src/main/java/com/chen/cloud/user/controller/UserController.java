package com.chen.cloud.user.controller;

import com.chen.cloud.user.domain.User;
import com.chen.cloud.user.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

    /**
     * 根据id查询user实体
     * @param id
     * @return
     */
    @GetMapping("user/{id}")
    public User findUserById(@PathVariable Long id){
        return userServices.findUserById(id);
    }

}
