package com.chen.cloud.user.controller;

import com.chen.cloud.user.domain.Movie;
import com.chen.cloud.user.services.MovieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @Autowired
    private MovieServices moiveServices;

    /**
     * 根据id查询user实体
     * @param id
     * @return
     */
    @GetMapping("movie/{id}")
    public Movie findMoiveById(@PathVariable Long id){
        return moiveServices.findMovieById(id);
    }

}
