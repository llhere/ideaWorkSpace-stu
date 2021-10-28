package com.chen.cloud.user.repository;

import com.chen.cloud.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 根据id查询user实体
     * @return
     */
    User findUserById(Long id);

}
