package com.chen.movie;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication   //springboot启动
@EnableEurekaClient      //eureka客户端
@EnableFeignClients      //feign
@EnableCircuitBreaker    //Hytrix
public class Hytrix8083 {

    public static void main(String[] args) {
        SpringApplication.run(Hytrix8083.class,args);
    }


}
