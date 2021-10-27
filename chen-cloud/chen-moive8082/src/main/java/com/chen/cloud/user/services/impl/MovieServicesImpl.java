package com.chen.cloud.user.services.impl;

import com.chen.cloud.user.domain.Movie;
import com.chen.cloud.user.repository.MovieRepository;
import com.chen.cloud.user.services.MovieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServicesImpl implements MovieServices {

    @Autowired
    private MovieRepository movieRepository;

    /**
     * 根据id查询useer实体
     * @param id
     * @return
     */
    @Override
    public Movie findMovieById(Long id) {
        return movieRepository.findMovieById(id);
    }

}
