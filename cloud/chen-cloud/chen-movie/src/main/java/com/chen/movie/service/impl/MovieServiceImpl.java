package com.chen.movie.service.impl;

import com.chen.movie.domain.User;
import com.chen.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User findUserById(Long id) {
        return restTemplate.getForObject("http://chen-cloud-user/user/" + id , User.class);
    }

}
