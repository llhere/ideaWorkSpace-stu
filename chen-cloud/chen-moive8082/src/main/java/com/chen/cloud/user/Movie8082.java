package com.chen.cloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Movie8082 {


    public static void main(String[] args) {
        SpringApplication.run(Movie8082.class,args);
    }
}
