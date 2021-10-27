package com.chen.cloud.user.repository;

import com.chen.cloud.user.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    /**
     * 根据id查询user实体
     * @return
     */
    Movie findMovieById(Long id);

}
