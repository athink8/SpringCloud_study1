package demo1_api.FeignClientService;

import demo1_api.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author: jz
 * Time: 2020/3/18 13:30
 **/
@FeignClient(value = "DEMO1PROVIDER1")
@RequestMapping("/user")
public interface UserClientService {

    @PostMapping("/")
    public boolean addUser(@RequestBody User user);

    @GetMapping("/id/{id}")
    public User findById(@PathVariable("id") Integer id);

    @GetMapping("/username/{username}")
    public User findByUsername(@PathVariable("username") String username);

    @GetMapping("/email/{email}")
    public User findByEmail(@PathVariable("email") String email);

    @GetMapping("/phone/{phone}")
    public User findByPhone(@PathVariable("phone") String phone);

    @GetMapping("/all")
    public List<User> findAll();

    /*服务发现*/
    @GetMapping("/discovery")
    public Object discovery();
}
