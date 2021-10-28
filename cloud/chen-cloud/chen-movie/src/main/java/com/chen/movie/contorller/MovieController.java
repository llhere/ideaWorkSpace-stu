package com.chen.movie.contorller;

import com.chen.movie.domain.User;
import com.chen.movie.feign.MovieFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    private MovieFeign movieFeign;

    @GetMapping("movie/{id}")
    public User findByUser(@PathVariable Long id) {
        return movieFeign.findUserById(id);
    }

}