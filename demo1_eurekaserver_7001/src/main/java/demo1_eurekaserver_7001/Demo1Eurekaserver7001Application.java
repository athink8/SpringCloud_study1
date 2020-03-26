package demo1_eurekaserver_7001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Demo1Eurekaserver7001Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Eurekaserver7001Application.class, args);
    }

}
