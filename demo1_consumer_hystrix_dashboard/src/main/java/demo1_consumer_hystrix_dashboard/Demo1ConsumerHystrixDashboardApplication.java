package demo1_consumer_hystrix_dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class Demo1ConsumerHystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo1ConsumerHystrixDashboardApplication.class, args);
    }

}
