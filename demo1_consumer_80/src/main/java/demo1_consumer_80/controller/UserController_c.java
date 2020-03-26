package demo1_consumer_80.controller;

import demo1_api.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * author: jz
 * Time: 2020/3/15 2:03
 **/
@RestController
@RequestMapping("/consumer")
public class UserController_c {
    private static final String REST_URL_PREFIX = "http://DEMO1PROVIDER1"+"/user";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/")
    public boolean addUser(@RequestBody User user) {
        System.out.println(user);
        return restTemplate.postForObject(REST_URL_PREFIX + "/",user, Boolean.class);
    }

    @GetMapping("/id/{id}")
    public User findById(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/id/" + id, User.class);
    }

    @GetMapping("/username/{username}")
    public User findByUsername(@PathVariable("username") String username) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/username/" + username, User.class);
    }

    @GetMapping("/email/{email}")
    public User findByEmail(@PathVariable("email") String email) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/email/" + email, User.class);
    }


    @GetMapping("/phone/{phone}")
    public User findByPhone(@PathVariable("phone") String phone) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/phone/" + phone, User.class);
    }

    @GetMapping("/all")
    public List<User> findAll() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/all", List.class);
    }
}
