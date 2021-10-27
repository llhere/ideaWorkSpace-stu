package com.chen.cloud.user.services;

import com.chen.cloud.user.domain.Movie;

public interface MovieServices {

    /**
     * 根据id查询User实体
     * @param id
     * @return
     */
    Movie findMovieById(Long id);

}
