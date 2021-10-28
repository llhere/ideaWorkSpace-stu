package com.chen.cloud.user.services.impl;

import com.chen.cloud.user.domain.User;
import com.chen.cloud.user.repository.UserRepository;
import com.chen.cloud.user.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    /**
     * 根据id查询useer实体
     * @param id
     * @return
     */
    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

}
