package com.chen.movie.feign;

import com.chen.movie.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="chen-cloud-user")
public interface MovieFeign {

    @RequestMapping(value="user/{id}" , method = RequestMethod.GET)
    public User findUserById(@PathVariable("id") Long id);

}