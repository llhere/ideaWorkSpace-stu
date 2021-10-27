package com.chen.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication   //springboot启动
@EnableEurekaClient      //eureka客户端
@EnableFeignClients      //feign
@EnableCircuitBreaker    //Hytrix
public class User8083 {

    public static void main(String[] args) {
        SpringApplication.run(User8083.class,args);
    }


}
