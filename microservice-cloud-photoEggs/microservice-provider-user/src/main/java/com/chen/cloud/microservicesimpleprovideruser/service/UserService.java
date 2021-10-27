package com.chen.cloud.microservicesimpleprovideruser.service;

import com.chen.cloud.microservicesimpleprovideruser.domain.User;

public interface UserService {

    /**
     * 根据id查询User实体
     * @param id
     * @return
     */
    User findUserById(Long id);

}
