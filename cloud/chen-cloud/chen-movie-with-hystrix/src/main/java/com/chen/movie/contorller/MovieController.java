package com.chen.movie.contorller;

import com.chen.movie.domain.User;
import com.chen.movie.feign.MovieFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    private MovieFeign movieFeign;

    @GetMapping("movie/{id}")
    @HystrixCommand(fallbackMethod = "findByIdFallback")    //快速失败方法，默认1秒检查失败，失败后进入findByIdFallback方法
    public User findByUser(@PathVariable Long id) {
        return movieFeign.findUserById(id);
    }


    /*
    findByUser 快速失败的方法
     */
    public User findByIdFallback(Long id){
        User user = new User();
        user.setId(1234L);
        user.setName("快速失败返回的User实体");
        return user;
    }



}