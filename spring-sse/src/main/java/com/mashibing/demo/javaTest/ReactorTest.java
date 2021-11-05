package com.mashibing.demo.javaTest;

import reactor.core.publisher.Flux;

/**
 * @Deacription
 * @Author chenpengwei
 * @Date 2021/11/5 9:26 上午
 * @Version 1.0
 **/
public class ReactorTest {


    /**
     * @description webflux、观察者消费者、onComplite，subcribe
     * @Param [args]
     * @return void
     * @author chenpengwei
     * @date 2021/11/5
     */
    public static void main(String[] args) {

        //同步创建只能next一次
        Flux.generate(fluxSink -> {
            fluxSink.next("1");
            fluxSink.complete();
        }).subscribe(System.out::println);


        //异步创建，可以next多次
        Flux.create(fluxSink -> {

            for (int i = 0; i < 10; i++) {
                fluxSink.next("xxoo:" + i);
                System.out.println(Thread.currentThread().getName());
            }

            fluxSink.complete();

        }).subscribe(System.out::println);



    }

}
