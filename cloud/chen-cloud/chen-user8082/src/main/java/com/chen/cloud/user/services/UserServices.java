package com.chen.cloud.user.services;

import com.chen.cloud.user.domain.User;

public interface UserServices {

    /**
     * 根据id查询User实体
     * @param id
     * @return
     */
    User findUserById(Long id);

}
