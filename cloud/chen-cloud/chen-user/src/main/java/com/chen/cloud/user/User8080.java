package com.chen.cloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class User8080 {


    public static void main(String[] args) {
        SpringApplication.run(User8080.class,args);
    }
}
