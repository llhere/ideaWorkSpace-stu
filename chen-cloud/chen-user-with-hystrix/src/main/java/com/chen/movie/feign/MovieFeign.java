package com.chen.movie.feign;

import com.chen.movie.domain.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="chen-moive")
public interface MovieFeign {

    @RequestMapping(value="movie/{id}" , method = RequestMethod.GET)
    public Movie findMoiveById(@PathVariable("id") Long id);

}