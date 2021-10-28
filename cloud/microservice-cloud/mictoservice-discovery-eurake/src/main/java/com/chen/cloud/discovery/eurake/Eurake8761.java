package com.chen.cloud.discovery.eurake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eurake8761 {

	public static void main(String[] args) {
		SpringApplication.run(Eurake8761.class, args);
	}

}
