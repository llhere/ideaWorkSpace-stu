package com.chen.movie.contorller;

import com.chen.movie.domain.Movie;
import com.chen.movie.feign.MovieFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private MovieFeign movieFeign;

    @GetMapping("/user/watchMovie/{id}")
    //快速失败方法，默认1秒检查，失败后进入findByIdFallback方法
    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public Movie findByUser(@PathVariable Long id) {
        return movieFeign.findMoiveById(id);
    }


    /*
    findByUser 快速失败的方法
     */
    public Movie findByIdFallback(Long id){
        Movie user = new Movie();
        user.setId(1234L);
        user.setNodename("node节点异常快速失败返回的User实体");
        return user;
    }



}