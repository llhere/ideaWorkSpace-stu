package com.chen.cloud.discovery.eurake.fegin;

import com.chen.cloud.discovery.config.Configuration1;
import com.chen.cloud.discovery.eurake.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "user-chen" , configuration = Configuration1.class)
public interface UserFeginClient {

    /**
     * 根据id查询user
     * @param Id
     * @return
     */
    @RequestMapping(value="/simple/{id}" , method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long Id);

    @RequestMapping(value = "/user" , method = RequestMethod.POST)
    public User postUser(User user);
}
