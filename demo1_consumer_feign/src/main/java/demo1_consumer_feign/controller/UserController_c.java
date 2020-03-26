package demo1_consumer_feign.controller;

import demo1_api.entities.User;
import demo1_consumer_feign.FeignClientService.UserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author: jz
 * Time: 2020/3/15 2:03
 **/
@RestController
@RequestMapping("/consumer")
public class UserController_c {

    @Autowired(required = false)
    UserClientService userClientService;

    @PostMapping("/")
    public boolean addUser(@RequestBody User user) {
        return userClientService.addUser(user);
    }

    @GetMapping("/id/{id}")
    public User findById(@PathVariable("id") Integer id) {
        return userClientService.findById(id);
    }

    @GetMapping("/username/{username}")
    public User findByUsername(@PathVariable("username") String username) {
        return userClientService.findByUsername(username);
    }

    @GetMapping("/email/{email}")
    public User findByEmail(@PathVariable("email") String email) {
        return userClientService.findByEmail(email);
    }

    @GetMapping("/phone/{phone}")
    public User findByPhone(@PathVariable("phone") String phone) {
        return userClientService.findByPhone(phone);
    }

    @GetMapping("/all")
    public List<User> findAll() {
        return userClientService.findAll();
    }

    /*服务发现*/
    @GetMapping("/discovery")
    public Object discovery() {
        return userClientService.discovery1();
    }
    /*测试*/
    @GetMapping("/test")
    public String test() {
        return "测试成功";
    }
}