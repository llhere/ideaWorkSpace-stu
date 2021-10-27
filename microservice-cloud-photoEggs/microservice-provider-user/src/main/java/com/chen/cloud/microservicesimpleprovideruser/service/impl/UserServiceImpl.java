package com.chen.cloud.microservicesimpleprovideruser.service.impl;

import com.chen.cloud.microservicesimpleprovideruser.domain.User;
import com.chen.cloud.microservicesimpleprovideruser.repository.UserRepository;
import com.chen.cloud.microservicesimpleprovideruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 根据id查询User实体
     * @param id
     * @return
     */
    @Override
    public User findUserById(Long id) {

        return userRepository.findUserById(id);
    }
}
