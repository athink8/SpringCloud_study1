package demo1_provider_8081.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import demo1_api.entities.User;
import demo1_provider_8081.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * author: jz
 * Time: 2020/3/14 22:50
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private EurekaDiscoveryClient eurekaDiscoveryClient; //服务发现类

    @PostMapping("/")
    public boolean addUser(@RequestBody User user) {
        System.out.println(user);
        return userService.addUser(user);
    }

    //    @HystrixCommand(fallbackMethod = "fallbackMethod")
    @GetMapping("/id/{id}")
    public User findById(@PathVariable("id") Integer id) {
        if (id == 8) {
            throw new RuntimeException("id==8");
        }
        return userService.findById(id);
    }

    @GetMapping("/username/{username}")
    public User findByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/email/{email}")
    public User findByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/phone/{phone}")
    public User findByPhone(@PathVariable("phone") String phone) {
        return userService.findByPhone(phone);
    }

    @HystrixCommand(fallbackMethod = "fallbackAll")
    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }

    /*服务发现*/
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    @GetMapping("/discovery")
    public Object discovery() {
        List list = eurekaDiscoveryClient.getServices();
        System.out.println("**********" + list);
        List<ServiceInstance> srvList = eurekaDiscoveryClient.getInstances("demo1_provider_8081");
        for (ServiceInstance element : srvList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.eurekaDiscoveryClient;
    }

    /*服务熔断返回方法*/
    public Object fallbackDiscovery() {
        return "服务错误！";
    }

    public List<User> fallbackAll() {
        List<User> users = new ArrayList<>();
        users.add(new User().setName("LIST 好烦"));
        return users;
    }

}
