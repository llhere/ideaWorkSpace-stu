package com.mashibing.demo.service;

import com.mashibing.demo.pojo.Person;
import org.springframework.stereotype.Service;

/**
 * @Deacription
 * @Author chenpengwei
 * @Date 2021/11/5 2:03 下午
 * @Version 1.0
 **/
@Service
public class PersonService {


    /**
     * @description 获取数据
     * @Param []
     * @return com.mashibing.demo.pojo.Person
     * @author chenpengwei
     * @date 2021/11/5
     */
    public Person getPerson() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Person();
    }

}
