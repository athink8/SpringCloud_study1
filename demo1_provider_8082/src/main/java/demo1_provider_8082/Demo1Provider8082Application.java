package demo1_provider_8082;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class Demo1Provider8082Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Provider8082Application.class, args);
    }

}
