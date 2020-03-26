package demo1_provider_8083.controller;

import demo1_api.entities.User;
import demo1_provider_8083.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/id/{id}")
    public User findById(@PathVariable("id") Integer id) {
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

    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }

    /*服务发现*/
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
}
