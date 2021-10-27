package com.chen.cloud.microservicesimpleprovideruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class User8082 {

	public static void main(String[] args) {
		SpringApplication.run(User8082.class, args);
	}

}
