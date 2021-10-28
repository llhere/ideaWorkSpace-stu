package chen.cloud.erurka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eureka8761 {

    public static void main(String[] args) {
        SpringApplication.run(Eureka8761.class,args);
    }
}
