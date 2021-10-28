package chen.cloud.erurka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eureka8764 {

    public static void main(String[] args) {
        SpringApplication.run(Eureka8764.class,args);
    }
}
