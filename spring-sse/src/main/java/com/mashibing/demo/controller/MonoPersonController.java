package com.mashibing.demo.controller;

import com.mashibing.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Deacription mono
 * @Author chenpengwei
 * @Date 2021/11/5 2:08 下午
 * @Version 1.0
 **/
@RestController
@RequestMapping("mono")
public class MonoPersonController {

    @Autowired
    PersonService personService;


    /**
     * @description mono获取数据例子，这里还是用一个线程
     * @Param []
     * @return reactor.core.publisher.Mono<java.lang.Object>
     * @author chenpengwei
     * @date 2021/11/5
     */
    @RequestMapping("getPerson")
    public Mono<Object> getMonoPerson(){


        //输出内容，测试是否异步
        System.out.println("11111");

        //创建数据序列，Mono和flux都可以，两者都是数据序列，Mono可以是0，
        // 1个元素，而Flux则可以是0到n个元素，两者可以互转
        Mono<Object> personMono = Mono.create(monoSink -> {

            monoSink.success(personService.getPerson());

        });

        //输出内容，测试是否异步
        System.out.println("22222");

        return personMono;
    }


}
