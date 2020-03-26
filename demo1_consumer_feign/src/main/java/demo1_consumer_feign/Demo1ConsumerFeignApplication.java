package demo1_consumer_feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
//扫描的是标记有@FeignClient(value = "xx")的类包
//@EnableFeignClients(basePackages = {"demo1_api.FeignClientService"})
@EnableFeignClients
public class Demo1ConsumerFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo1ConsumerFeignApplication.class, args);
    }

}
