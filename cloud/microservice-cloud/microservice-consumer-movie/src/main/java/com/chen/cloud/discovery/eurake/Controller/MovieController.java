package com.chen.cloud.discovery.eurake.Controller;

import com.chen.cloud.discovery.eurake.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 用户看电影获取user服务的user实体
     */
    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id){

        return restTemplate.getForObject("http://user-chen/simple/" + id , User.class);
    }


}
