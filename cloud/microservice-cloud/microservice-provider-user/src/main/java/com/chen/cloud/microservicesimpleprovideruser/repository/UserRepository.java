package com.chen.cloud.microservicesimpleprovideruser.repository;

import com.chen.cloud.microservicesimpleprovideruser.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 根据id查询User用户
     * @param id
     * @return
     */
    User findUserById(Long id);

}
