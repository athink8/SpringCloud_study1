package demo1_consumer_80;

import MyRibbonCofig.MyRibbonRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "DEMO1PROVIDER1",configuration = MyRibbonRule.class)
//@RibbonClients(defaultConfiguration = MyRibbonRule.class)
public class Demo1Consumer80Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Consumer80Application.class, args);
    }

}
