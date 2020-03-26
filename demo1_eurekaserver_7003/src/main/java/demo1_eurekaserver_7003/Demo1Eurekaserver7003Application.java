package demo1_eurekaserver_7003;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Demo1Eurekaserver7003Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Eurekaserver7003Application.class, args);
    }

}
