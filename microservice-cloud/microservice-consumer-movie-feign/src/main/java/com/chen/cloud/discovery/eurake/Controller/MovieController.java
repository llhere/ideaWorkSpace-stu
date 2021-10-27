package com.chen.cloud.discovery.eurake.Controller;

import com.chen.cloud.discovery.eurake.domain.User;
import com.chen.cloud.discovery.eurake.fegin.UserFeginClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {


    @Autowired
    private UserFeginClient userFeginClient;

    /**
     * 用户看电影获取user服务的user实体
     */
    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id){

        return userFeginClient.findById(id);
    }

    /**
     * 返回获取到的user信息
     * @param user
     * @return
     */
    @PostMapping("/user")
    public User postUser(@RequestBody User user){
        return userFeginClient.postUser(user);
    }

}
